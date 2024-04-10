package com.roliveira.spendaholic.ui.screens.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.utils.Utils

@Composable
fun DetailsPieChart(
    categoryMap: Map<Category, Float>,
    colors: List<Color>
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        items(categoryMap.entries.toList()) { (category, value) ->
            DetailsPieChartItem(
                data = Pair(category.name, value),
                color = colors[categoryMap.keys.indexOf(category)]
            )
        }
    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Float>,
    height: Dp = 35.dp,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(10.dp)
                )
                .size(height)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = data.first,
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "$${Utils.formatFloatWithTwoDecimalPlaces(data.second)}",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        }

    }
}