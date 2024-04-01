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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography

@Composable
fun AmountSection(
    amountState: String,
    onAmountChange: (String) -> Unit
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
                        painter = painterResource(id = R.drawable.dollar),
                        contentDescription = "Dollar symbol",
                        tint = colorResource(id = R.color.dark_blue),
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
}