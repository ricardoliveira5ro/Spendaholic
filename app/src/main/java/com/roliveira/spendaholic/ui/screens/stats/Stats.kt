package com.roliveira.spendaholic.ui.screens.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.data.Currencies
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Currency
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme
import com.roliveira.spendaholic.utils.Utils
import java.util.Calendar
import java.util.Date

@Composable
fun Stats(
    expenses: List<Expense>,
    currency: Currency
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

        BarChartSection(expenses = expenses)

        val expensesByCategory = expenses.groupBy { it.category }
        var category = Categories.defaultCategory

        val topCategory = expensesByCategory.maxByOrNull { it.value.sumOf { expense -> expense.amount.toDouble() }.toFloat() }

        var amount = 0f
        if (topCategory != null) {
            category = topCategory.key
            amount = topCategory.value.sumOf { it.amount.toDouble() }.toFloat()
        }

        StatisticCategory(
            title = "Top expenses category",
            category = category,
            text = "- ${currency.symbol}${Utils.formatFloatWithTwoDecimalPlaces(amount)}"
        )


        val frequentlyCategory = expensesByCategory.maxByOrNull { it.value.count() }

        var count = 0
        if (frequentlyCategory != null) {
            category = frequentlyCategory.key
            count = frequentlyCategory.value.count()
        }

        StatisticCategory(
            title = "Frequently Category",
            category = category,
            text = "${count}x"
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "Average",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val expensesByWeek = expenses.groupBy { Calendar.getInstance().apply {time = it.date }.get(Calendar.WEEK_OF_YEAR) }
                var averageWeek = 0f

                if (expensesByWeek.isNotEmpty()) {
                    averageWeek =
                        (expensesByWeek.values.sumOf { expenses ->
                            expenses.sumOf { it.amount.toDouble() }
                        }.toFloat()
                    / expensesByWeek.values.size)
                }
                AverageTransaction(
                    title = "Week",
                    amount = averageWeek,
                    currency = currency
                )

                val expenseByMonth = expenses.groupBy { Calendar.getInstance().apply {time = it.date }.get(Calendar.MONTH) }
                var averageMonth = 0f

                if (expenseByMonth.isNotEmpty()) {
                    averageMonth =
                        (expenseByMonth.values.sumOf { expenses ->
                            expenses.sumOf { it.amount.toDouble() }
                        }.toFloat()
                                / expenseByMonth.values.size)
                }
                AverageTransaction(
                    title = "Month",
                    amount = averageMonth,
                    currency = currency
                )

                val expenseByYear = expenses.groupBy { Calendar.getInstance().apply {time = it.date }.get(Calendar.YEAR) }
                var averageYear = 0f

                if (expenseByYear.isNotEmpty()) {
                    averageYear =
                        (expenseByYear.values.sumOf { expenses ->
                            expenses.sumOf { it.amount.toDouble() }
                        }.toFloat()
                                / expenseByYear.values.size)
                }
                AverageTransaction(
                    title = "Year",
                    amount = averageYear,
                    currency = currency
                )
            }
        }
    }
}

@Composable
fun StatisticCategory(
    title: String,
    category: Category,
    text: String
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        CategoryItem(
            category = category,
            text = text
        )
    }
}

@Composable
fun CategoryItem(
    category: Category,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .background(color = category.backgroundColor, shape = RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = "Category",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = category.name,
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            )
        }

        Text(
            text = text,
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}

@Composable
fun AverageTransaction(
    title: String,
    amount: Float,
    currency: Currency
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Row (
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.dark_blue),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = "${Utils.formatFloatWithTwoDecimalPlaces(amount)}${currency.symbol}",
                color = Color.White,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
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
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 50f, date = getDateForYear(currentYear - 2), repeatable = Repeatable.NOT_REPEATABLE),
                Expense(3, dummyCategories[2], note = "Electric bill", amount = 54.23f, date = getDateForYear(currentYear - 2), repeatable = Repeatable.NOT_REPEATABLE)
            )

            Stats(expenses = dummyExpensesList, Currencies.currencies.first())
        }
    }
}

private fun getDateForYear(year: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    return calendar.time
}