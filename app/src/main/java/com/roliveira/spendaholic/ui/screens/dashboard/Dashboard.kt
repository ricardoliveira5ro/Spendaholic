package com.roliveira.spendaholic.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
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
fun Dashboard(
    expenses: List<Expense>,
    onNewExpenseClick: () -> Unit,
    onScheduleClick: () -> Unit,
    onSummaryClick: () -> Unit,
    onTransactionClick: (Int) -> Unit
) {
    var selectedMonthIndex by rememberSaveable { mutableIntStateOf(Calendar.getInstance().get(Calendar.MONTH)) }

    val filteredExpenses = remember(expenses, selectedMonthIndex) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = selectedMonthIndex

        expenses.filter { expense ->
            val expenseYear = Calendar.getInstance().apply { time = expense.date }.get(Calendar.YEAR)
            val expenseMonth = Calendar.getInstance().apply { time = expense.date }.get(Calendar.MONTH)
            expenseYear == currentYear && expenseMonth == currentMonth
        }
    }.sortedByDescending { it.date }.sortedByDescending { it.id }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.dark_blue),
                    shape = RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Dropdown(
                selectedMonthIndex = selectedMonthIndex,
                onMonthSelected = { selectedMonthIndex = it }
            )

            Text(
                text = "Spends this month",
                color = colorResource(id = R.color.grey),
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )

            Text(
                text = "$${Utils.formatFloatWithTwoDecimalPlaces(filteredExpenses.sumOf { it.amount.toDouble() }.toFloat())}",
                color = colorResource(id = R.color.white),
                fontFamily = Typography.sanFranciscoText,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )

            ActionBar(
                onNewExpenseClick = { onNewExpenseClick() },
                onScheduleClick = { onScheduleClick() },
                onSummaryClick = { onSummaryClick() }
            )
        }


        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Transactions",
                    color = Color.Black,
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            LazyColumn {
                items(filteredExpenses) { expense ->
                    TransactionItem(
                        expense = expense,
                        onTransactionClick = { onTransactionClick(expense.id) }
                    )
                }
            }
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
                Category(1, Categories.defaultCategories[0].name, Utils.categoryTransactionIcon(Categories.defaultCategories[0].id), Utils.categoryTransactionColor(Categories.defaultCategories[0].id)),
                Category(2, Categories.defaultCategories[1].name, Utils.categoryTransactionIcon(Categories.defaultCategories[1].id), Utils.categoryTransactionColor(Categories.defaultCategories[1].id)),
                Category(3, Categories.defaultCategories[2].name, Utils.categoryTransactionIcon(Categories.defaultCategories[2].id), Utils.categoryTransactionColor(Categories.defaultCategories[2].id))
            )

            val dummyExpensesList = listOf(
                Expense(1, dummyCategories[0], note = null, amount = 23.42f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(2, dummyCategories[1], note = "Madrid", amount = 134f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = Date(), repeatable = Repeatable.NOT_REPEATABLE)
            )
            Dashboard(dummyExpensesList, {}, {}, {}, {})
        }
    }
}