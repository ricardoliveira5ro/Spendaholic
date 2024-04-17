package com.roliveira.spendaholic.ui

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.data.Currencies
import com.roliveira.spendaholic.data.DataStoreMapper
import com.roliveira.spendaholic.data.SettingsDataStoreMapper
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.model.Settings
import com.roliveira.spendaholic.utils.Utils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToScreen = MutableLiveData<String?>()
    val navigateToScreen: LiveData<String?> = _navigateToScreen

    private val dataStoreMapper = DataStoreMapper.getInstance(getApplication())
    private val settingsDataStoreMapper = SettingsDataStoreMapper.getInstance(getApplication())

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    private val _settings = MutableLiveData<Settings>()
    val settings: LiveData<Settings> = _settings

    init {
        loadExpenses()
        loadSettings()
    }

    fun navigateTo(screenRoute: String) {
        _navigateToScreen.value = screenRoute
    }

    fun navigationDone() {
        _navigateToScreen.value = null
    }

    fun loadSettings() {
        viewModelScope.launch {
            settingsDataStoreMapper.loadSettings().collect { settings ->
                _settings.value = settings

                if (settings.categories.isEmpty()) {
                    saveSettings(Settings(Currencies.currencies[0], Categories.defaultCategories))
                }
            }
        }
    }

    fun saveSettings(settings: Settings) {
        viewModelScope.launch {
            val success = settingsDataStoreMapper.saveSettings(settings).first()

            if (!success) Log.e("MainViewModel", "Error trying to save settings: ´saveSettings´")
        }
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

        val category = settings.value?.categories.orEmpty().find { it.id == categoryId } ?: Categories.defaultCategory
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

    fun saveCategory(id: Int, name: String, icon: Int, color: Color) {
        val categoriesToSave = _settings.value?.categories.orEmpty().toMutableList()

        if (id != -1) {
            val category = Category(id, name, icon, color)
            val index = categoriesToSave.indexOfFirst { it.id == id }
            categoriesToSave[index] = category
        }
        else {
            val nextId = Utils.getNextCategoryId(settings.value?.categories.orEmpty())
            val category = Category(nextId, name, icon, color)
            categoriesToSave.add(category)
        }

        val settings = Settings(
            currency = _settings.value?.currency ?: Currencies.currencies[0],
            categories = categoriesToSave
        )

        viewModelScope.launch {
            val success = settingsDataStoreMapper.saveSettings(settings).first()

            if (!success) Log.e("MainViewModel", "Error trying to save settings: ´saveCategory´")
        }
    }
}