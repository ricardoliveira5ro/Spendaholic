package com.roliveira.spendaholic.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.utils.Utils

@Composable
fun TransactionItem(
    expense: Expense,
    onTransactionClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onTransactionClick(expense.id) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .background(color = expense.category.backgroundColor, shape = RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = expense.category.icon),
                    contentDescription = "Category",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = expense.category.name,
                    color = Color.Black,
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = Utils.dateTimeDashboardFormat(expense.date),
                    color = colorResource(id = R.color.light_grey),
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
            }
        }

        Text(
            text = "- $${Utils.formatFloatWithTwoDecimalPlaces(expense.amount)}",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}