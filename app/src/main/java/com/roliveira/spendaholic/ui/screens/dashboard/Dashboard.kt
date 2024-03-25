package com.roliveira.spendaholic.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@Composable
fun Dashboard() {
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
                        onClick = {  }
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
                        onClick = {  }
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
                        onClick = {  }
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