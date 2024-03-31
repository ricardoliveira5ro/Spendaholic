package com.roliveira.spendaholic.ui.screens.dashboard

import android.app.Application
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
import com.roliveira.spendaholic.DummyData
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.ui.MainViewModel
import com.roliveira.spendaholic.ui.Screen
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@Composable
fun Dashboard(viewModel: MainViewModel = MainViewModel(Application())) {
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
            Dropdown()

            Text(
                text = "Spends this month",
                color = colorResource(id = R.color.grey),
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )

            Text(
                text = "$1899.51",
                color = colorResource(id = R.color.white),
                fontFamily = Typography.sanFranciscoText,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )

            ActionBar(
                onNewExpenseClick = { viewModel.navigateTo(Screen.NewExpense.route) }
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
                items(DummyData.transactions) {
                    transaction -> TransactionItem(transaction = transaction, onTransactionClick = { viewModel.navigateTo(Screen.NewExpense.route) })
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
            Dashboard()
        }
    }
}