package com.roliveira.spendaholic.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
                            .background(color = Color(0xFFF7838C), shape = RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.entertainment),
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
                            text = "Netflix",
                            color = Color.Black,
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Dec 5th, 22:00",
                            color = colorResource(id = R.color.light_grey),
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                        )
                    }
                }

                Text(
                    text = "- $12.99",
                    color = Color.Black,
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

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
                            .background(color = Color(0xFFFAD1A2), shape = RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.grocery),
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
                            text = "Groceries",
                            color = Color.Black,
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Dec 5th, 20:38",
                            color = colorResource(id = R.color.light_grey),
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                        )
                    }
                }

                Text(
                    text = "- $45.37",
                    color = Color.Black,
                    fontFamily = Typography.sanFranciscoRounded,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
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