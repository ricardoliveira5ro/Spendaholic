package com.roliveira.spendaholic.ui.screens.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import java.util.Date

@Composable
fun Schedule(
    expenses: List<Expense>,
    onNavigateBack: () -> Unit,
    onNewExpense: () -> Unit,
    onExpenseClick: (Int) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScheduleHeader(
            onNavigateBack = onNavigateBack,
            onNewExpense = onNewExpense
        )

        Spacer(modifier = Modifier.height(16.dp))

        val dailyExpenses = expenses.filter { it.repeatable == Repeatable.DAY && !it.isWorkRepeatable }
        if (dailyExpenses.isNotEmpty()) {
            DailyExpenses(
                expenses = dailyExpenses,
                onExpenseClick = onExpenseClick
            )
        }

        val weeklyExpenses = expenses.filter { it.repeatable == Repeatable.WEEK && !it.isWorkRepeatable }
        if (weeklyExpenses.isNotEmpty()) {
            WeeklyExpenses(
                expenses = weeklyExpenses
            )
        }

        val monthlyExpenses = expenses.filter { it.repeatable == Repeatable.MONTH && !it.isWorkRepeatable }
        if (monthlyExpenses.isNotEmpty()) {
            MonthlyExpenses(
                expenses = monthlyExpenses
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
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
                Expense(1, dummyCategories[0], note = null, amount = 23.42f, date = Date(), repeatable = Repeatable.DAY),
                Expense(2, dummyCategories[1], note = null, amount = 134f, date = Date(), repeatable = Repeatable.DAY),
                Expense(3, dummyCategories[1], note = null, amount = 134f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(4, dummyCategories[2], note = null, amount = 134f, date = Date(), repeatable = Repeatable.WEEK),
                Expense(5, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = Date(), repeatable = Repeatable.MONTH)
            )

            Schedule(dummyExpensesList, {}, {}, {})
        }
    }
}