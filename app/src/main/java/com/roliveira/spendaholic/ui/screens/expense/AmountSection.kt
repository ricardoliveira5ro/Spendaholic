package com.roliveira.spendaholic.ui.screens.expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Currency
import com.roliveira.spendaholic.utils.Utils

@Composable
fun AmountSection(
    currency: Currency,
    amountState: String,
    onAmountChange: (String) -> Unit,
    attemptedSaveError: Boolean
) {
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
            TextField(
                value = amountState,
                onValueChange = { if(it.matches(Regex("^\\d*(\\.\\d{0,2})?\$"))) { onAmountChange(it) } },
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
                        painter = painterResource(id = Utils.currencyIconMapper(currency)),
                        contentDescription = currency.name,
                        tint = colorResource(id = R.color.dark_blue),
                        modifier = Modifier.size(28.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                    focusedIndicatorColor = if (attemptedSaveError) Color.Red else Color.Transparent,
                    unfocusedContainerColor = colorResource(id = R.color.light_grey_boxes),
                    unfocusedIndicatorColor = if (attemptedSaveError) Color.Red else Color.Transparent,
                    disabledContainerColor = colorResource(id = R.color.light_grey_boxes),
                    disabledIndicatorColor = Color.Transparent
                ),
                supportingText = {
                    if (attemptedSaveError) {
                        Text(
                            text = "Required field",
                            color = Color.Red,
                            fontFamily = Typography.sanFranciscoRounded,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    }
                }
            )
        }
    }
}