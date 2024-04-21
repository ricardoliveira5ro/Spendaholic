package com.roliveira.spendaholic.ui.screens.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import com.roliveira.spendaholic.utils.Utils
import java.util.Calendar
import java.util.Date

@Composable
fun Stats(
    expenses: List<Expense>
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Statistics",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            val yearlyExpenses = expenses.groupBy { expense ->
                val calendar = Calendar.getInstance()
                calendar.time = expense.date
                calendar.get(Calendar.YEAR)
            }

            val dataList = mutableListOf<Float>()
            val datesList = mutableListOf<Int>()

            yearlyExpenses.forEach { (year, expensesForYear) ->
                val totalAmountForYear = expensesForYear.sumOf { it.amount.toDouble() }.toFloat()
                dataList.add(totalAmountForYear)
                datesList.add(year)
            }

            val maxValue = dataList.maxOrNull() ?: 0f

            // Normalize data to range [0, 1] to fit the graph
            val normalizedData = dataList.map { it / maxValue }

            BarChart(
                graphBarData = normalizedData,
                xAxisScaleData = datesList,
                barData = dataList.map { it.toInt() },
                height = 300.dp,
                barWidth = 20.dp,
                barColor = colorResource(id = R.color.dark_blue),
                barArrangement = Arrangement.SpaceEvenly
            )
        }
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
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)

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
                Expense(2, dummyCategories[1], note = "Madrid", amount = 134f, date = getDateForYear(currentYear - 1), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = getDateForYear(currentYear - 2), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = getDateForYear(currentYear - 2), repeatable = Repeatable.NOT_REPEATABLE)
            )

            Stats(expenses = dummyExpensesList)
        }
    }
}

private fun getDateForYear(year: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    return calendar.time
}