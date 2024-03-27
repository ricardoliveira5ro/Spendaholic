package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.DummyData
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewExpense() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "New Expense",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Amount",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                var amountState by remember { mutableStateOf("") }
                TextField(
                    value = amountState,
                    onValueChange = { if(it.matches(Regex("^\\d*(\\.\\d{0,2})?\$"))) { amountState = it } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = Typography.sanFranciscoRounded,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    ),
                    leadingIcon = {
                       Icon(
                           painter = painterResource(id = R.drawable.dollar),
                           contentDescription = "Dollar symbol",
                           tint = colorResource(id = R.color.blue),
                           modifier = Modifier.size(28.dp)
                       )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledContainerColor = colorResource(id = R.color.light_grey_boxes),
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Select category",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            var selectedCategoryIndex by rememberSaveable { mutableIntStateOf(0) }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                DummyData.categories.onEachIndexed { index, category ->

                    val isSelected = selectedCategoryIndex == index
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .background(
                                color = if (isSelected) colorResource(id = R.color.box_selected)
                                            else colorResource(id = R.color.light_grey_boxes),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                width = if (isSelected) 1.dp else 0.dp,
                                color = if (isSelected) colorResource(id = R.color.blue)
                                            else Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .height(32.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedCategoryIndex = index
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon),
                            contentDescription = "Entertainment category",
                            modifier = Modifier.size(22.dp),
                            tint = if (isSelected) colorResource(id = R.color.blue) else Color.Black
                        )

                        Text(
                            text = category.name,
                            color = if (isSelected) colorResource(id = R.color.blue) else Color.Black,
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp
                        )
                    }

                    
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewExpensePreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewExpense()
        }
    }
}