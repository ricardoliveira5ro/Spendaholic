package com.roliveira.spendaholic.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
fun SettingsHeader(
    onNavigateBack: () -> Unit,
    onSaveSettings: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onNavigateBack() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Go back",
                tint = Color.Black
            )
        }

        Text(
            text = "Settings",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        IconButton(
            onClick = { onSaveSettings() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.save),
                contentDescription = "Go back",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}