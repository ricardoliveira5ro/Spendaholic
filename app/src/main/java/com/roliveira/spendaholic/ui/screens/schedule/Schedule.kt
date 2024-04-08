package com.roliveira.spendaholic.ui.screens.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import java.util.Date

@Composable
fun Schedule(
    expenses: List<Expense>
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Go back",
                    tint = Color.Black
                )
            }

            Text(
                text = "Schedule",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoText,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )


            IconButton(
                onClick = {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_vector),
                    contentDescription = "Add expense",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        LazyColumn {
            items(expenses.filter { it.repeatable == Repeatable.DAY }) {

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
                Expense(4, dummyCategories[1], note = "Madrid", amount = 134f, date = Date(), repeatable = Repeatable.WEEK),
                Expense(5, dummyCategories[2], note = "Electric bill", amount = 54.55f, date = Date(), repeatable = Repeatable.MONTH)
            )

            Schedule(expenses = dummyExpensesList)
        }
    }
}