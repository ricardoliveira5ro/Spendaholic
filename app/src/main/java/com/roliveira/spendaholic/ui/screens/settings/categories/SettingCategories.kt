package com.roliveira.spendaholic.ui.screens.settings.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.fonts.Typography

@Composable
fun SettingCategories() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Categories",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        IconButton(
            onClick = {  }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.long_right_arrow),
                contentDescription = "Go back",
                tint = Color.Black
            )
        }
    }
}