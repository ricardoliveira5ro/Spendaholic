package com.roliveira.spendaholic.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography

@Composable
fun ActionBar(
    onNewExpenseClick: () -> Unit,
    onScheduleClick: () -> Unit,
    onSummaryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 40.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.blue_pastel)),
                onClick = { onNewExpenseClick() }
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource(id = R.drawable.add),
                    tint = colorResource(id = R.color.light_grey),
                    contentDescription = "Add"
                )
            }

            Text(
                text = "Expense",
                color = colorResource(id = R.color.light_grey),
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.blue_pastel)),
                onClick = { onScheduleClick() }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(id = R.drawable.schedule),
                    tint = colorResource(id = R.color.light_grey),
                    contentDescription = "Schedule"
                )
            }

            Text(
                text = "Schedule",
                color = colorResource(id = R.color.light_grey),
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.blue_pastel)),
                onClick = { onSummaryClick() }
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource(id = R.drawable.summary),
                    tint = colorResource(id = R.color.light_grey),
                    contentDescription = "Summary"
                )
            }

            Text(
                text = "Summary",
                color = colorResource(id = R.color.light_grey),
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
    }
}