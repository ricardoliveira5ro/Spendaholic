package com.roliveira.spendaholic.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.utils.Utils
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DailyWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("DailyWorker", "--> do work")

        return try {
            val dataStoreMapper = DataStoreMapper.getInstance(applicationContext as Application)
            val expenses = dataStoreMapper.loadExpenses().first()

            val currentDate = Calendar.getInstance()

            val repeatableExpensesDueToday = expenses.filter { expense ->
                expense.repeatable != Repeatable.NOT_REPEATABLE && isExpenseDueToday(expense, currentDate)
            }

            val newExpensesToSave = mutableListOf<Expense>()
            repeatableExpensesDueToday.forEach { expense ->
                val newExpense = expense.copy(
                    id = Utils.getNextId(expenses),
                    date = Utils.dateTime(
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentDate.time),
                        "00:00"
                    )
                )

                newExpensesToSave.add(newExpense)
            }

            val updatedExpenses = expenses.toMutableList()
            updatedExpenses.addAll(newExpensesToSave)

            dataStoreMapper.saveExpenses(updatedExpenses.toList()).first()

            Result.success()
        } catch (e: Exception) {
            Log.e("DailyWorker", "Error processing repeatable expenses: ${e.message}")
            Result.failure()
        }
    }

    private fun isExpenseDueToday(expense: Expense, currentDate: Calendar): Boolean {
        val expenseDate = Calendar.getInstance().apply { time = expense.date }
        return when (expense.repeatable) {
            Repeatable.DAY -> true
            Repeatable.WEEK -> expenseDate.get(Calendar.DAY_OF_WEEK) == currentDate.get(Calendar.DAY_OF_WEEK)
            Repeatable.MONTH -> expenseDate.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)
            Repeatable.YEAR -> expenseDate.get(Calendar.DAY_OF_YEAR) == currentDate.get(Calendar.DAY_OF_YEAR)
            else -> false
        }
    }
}