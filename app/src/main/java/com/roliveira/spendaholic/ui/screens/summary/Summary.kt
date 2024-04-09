package com.roliveira.spendaholic.ui.screens.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import com.roliveira.spendaholic.utils.Utils
import java.util.Calendar
import java.util.Date

@Composable
fun Summary(
    expenses: List<Expense>
) {
    var currentMonth by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    var currentYear by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }

    val filteredExpenses = remember(currentMonth) {
        expenses.filter { expense ->
            val calendar = Calendar.getInstance()
            calendar.time = expense.date
            calendar.get(Calendar.MONTH) == currentMonth
        }
    }

    val categoryAmountMap = mutableMapOf<Category, Float>()

    for (expense in filteredExpenses) {
        val category = expense.category

        val currentAmount = categoryAmountMap.getOrDefault(category, 0f)
        val newAmount = currentAmount + expense.amount
        categoryAmountMap[category] = newAmount
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateNavigation(
            currentMonth = currentMonth,
            currentYear = currentYear,
            onMonthChange = { month, year ->
                currentMonth = month
                currentYear = year
            }
        )

        PieChart(
            categoryMap = categoryAmountMap
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val dummyCategories = listOf(
                Category(1, Categories.defaultCategories[0].name, Utils.categoryTransactionIcon(
                    Categories.defaultCategories[0].id), Utils.categoryTransactionColor(Categories.defaultCategories[0].id)),
                Category(2, Categories.defaultCategories[1].name, Utils.categoryTransactionIcon(
                    Categories.defaultCategories[1].id), Utils.categoryTransactionColor(Categories.defaultCategories[1].id)),
                Category(3, Categories.defaultCategories[2].name, Utils.categoryTransactionIcon(
                    Categories.defaultCategories[2].id), Utils.categoryTransactionColor(Categories.defaultCategories[2].id))
            )

            val dummyExpensesList = listOf(
                Expense(1, dummyCategories[0], note = null, amount = 23.42f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(2, dummyCategories[1], note = "Madrid", amount = 134f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE)
            )
            Summary(expenses = dummyExpensesList)
        }
    }
}