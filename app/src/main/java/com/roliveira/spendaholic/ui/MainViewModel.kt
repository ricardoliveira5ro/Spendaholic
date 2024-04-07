package com.roliveira.spendaholic.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.data.DataStoreMapper
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.utils.Utils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToScreen = MutableLiveData<String?>()
    val navigateToScreen: LiveData<String?> = _navigateToScreen

    private val dataStoreMapper = DataStoreMapper.getInstance(getApplication())

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    init {
        loadExpenses()
    }

    fun navigateTo(screenRoute: String) {
        _navigateToScreen.value = screenRoute
    }

    fun navigationDone() {
        _navigateToScreen.value = null
    }

    fun loadExpenses() {
        viewModelScope.launch {
            dataStoreMapper.loadExpenses().collect { expenses ->
                _expenses.value = expenses
            }
        }
    }

    fun saveExpense(id: Int, amount: Float, categoryId: Int, note: String, date: String, time: String, repeatable: Repeatable) {
        val expensesToSave = _expenses.value.orEmpty().toMutableList()

        val category = Categories.defaultCategories.find { it.id == categoryId } ?: Categories.defaultCategory
        val dateTime = Utils.dateTime(date, time)

        if (id != -1) {
            val expense = Expense(id, category, note, amount, dateTime, repeatable)
            val index = expensesToSave.indexOfFirst { it.id == expense.id }
            expensesToSave[index] = expense
        }
        else {
            val nextId = Utils.getNextId(expenses.value.orEmpty())
            val expense = Expense(nextId, category, note, amount, dateTime, repeatable)
            expensesToSave.add(expense)
        }

        viewModelScope.launch {
            val success = dataStoreMapper.saveExpenses(expensesToSave.toList()).first()

            if (!success) Log.e("MainViewModel", "Error trying to save expenses: ´saveExpense´")
        }
    }

    fun deleteExpense(id: Int) {
        val newListExpenses = _expenses.value.orEmpty().toMutableList()
        newListExpenses.removeIf { it.id == id }

        viewModelScope.launch {
            val success = dataStoreMapper.saveExpenses(newListExpenses.toList()).first()

            if (!success) Log.e("MainViewModel", "Error trying to delete expenses: ´deleteExpense´")
        }
    }
}