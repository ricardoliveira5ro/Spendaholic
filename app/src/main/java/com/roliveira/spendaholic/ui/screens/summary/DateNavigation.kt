package com.roliveira.spendaholic.ui.screens.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.fonts.Typography
import java.time.Month

@Composable
fun DateNavigation(
    currentMonth: Int,
    currentYear: Int,
    onMonthChange: (Int, Int) -> Unit
) {
    val monthNames = Month.entries.map { month -> month.toString().lowercase().replaceFirstChar { it.uppercase() } }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = {
            val newMonth = if (currentMonth == 0) 11 else currentMonth - 1
            val newYear = if (currentMonth == 0) currentYear - 1 else currentYear
            onMonthChange(newMonth, newYear)
        }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous Month")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = monthNames[currentMonth],
                textAlign = TextAlign.Center,
                fontFamily = Typography.sanFranciscoText,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentYear.toString(),
                textAlign = TextAlign.Center,
                fontFamily = Typography.sanFranciscoText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }
        IconButton(onClick = {
            val newMonth = (currentMonth + 1) % 12
            val newYear = if (newMonth == 0) currentYear + 1 else currentYear
            onMonthChange(newMonth, newYear)
        }) {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next Month")
        }
    }
}