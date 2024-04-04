package com.roliveira.spendaholic.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.roliveira.spendaholic.ProtoCategory
import com.roliveira.spendaholic.ProtoExpense
import com.roliveira.spendaholic.ProtoExpenseItems
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DataStoreMapper(private val application: Application) {
    private val Context.protoDataStore: DataStore<ProtoExpenseItems> by dataStore(
        fileName = "expenses.pb",
        serializer = ExpenseSerializer
    )

    companion object {
        @Volatile
        private var INSTANCE: DataStoreMapper? = null

        fun getInstance(application: Application): DataStoreMapper {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreMapper(application)
                INSTANCE = instance
                instance
            }
        }
    }

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    suspend fun saveExpenses(expenses: List<Expense>) : Flow<Boolean> {
        return flow {
            application.protoDataStore.updateData { store ->
                val storeItem = expenses.map { it.toProtoExpense() }
                store.toBuilder()
                    .clearExpenses()
                    .addAllExpenses(storeItem)
                    .build()
            }
            emit(true)
        } .catch { error ->
            Log.e("DataStoreHelper", "Error saving list of expenses to datastore: ´saveExpenses´: ${error.message}", error)
            emit(false)
        }
    }

    fun loadExpenses(): Flow<List<Expense>> {
        return application.protoDataStore.data.map {store ->
            store.expensesList.map { it.toExpense() }
        }
    }

    private fun Expense.toProtoExpense(): ProtoExpense {
        return ProtoExpense.newBuilder()
            .setId(this.id)
            .setCategory(this.category.toProtoCategory())
            .setNote(this.note)
            .setAmount(this.amount)
            .setDate(dateFormat.format(this.date))
            .setRepeat(this.repeatable.name)
            .build()
    }

    private fun ProtoExpense.toExpense(): Expense {
        return Expense(
            id = this.id,
            category = this.category.toCategory(),
            note = this.note,
            amount = this.amount,
            date = dateFormat.parse(this.date) ?: Date(),
            repeatable = this.repeat.toRepeatable()
        )
    }

    private fun Category.toProtoCategory(): ProtoCategory {
        return ProtoCategory.newBuilder()
            .setId(this.id)
            .setName(this.name)
            .build()
    }

    private fun ProtoCategory.toCategory(): Category {
        return Category(
            id = this.id,
            name = this.name,
            icon = Utils.categoryTransactionIcon(this.id),
            backgroundColor = Utils.categoryTransactionColor(this.id)
        )
    }

    private fun String.toRepeatable(): Repeatable {
        return when (this) {
            "NOT_REPEATABLE" -> Repeatable.NOT_REPEATABLE
            "DAY" -> Repeatable.DAY
            "WEEK" -> Repeatable.WEEK
            "MONTH" -> Repeatable.MONTH
            "YEAR" -> Repeatable.YEAR
            else -> Repeatable.NOT_REPEATABLE
        }
    }
}