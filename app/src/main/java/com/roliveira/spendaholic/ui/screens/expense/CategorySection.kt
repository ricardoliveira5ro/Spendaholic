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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.fonts.Typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySection(
    selectedCategoryIndex: Int,
    onSelectedCategoryIndexChange: (Int) -> Unit
) {
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

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Categories.defaultCategories.onEachIndexed { index, category ->
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
                            color = if (isSelected) colorResource(id = R.color.dark_blue)
                            else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .height(32.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onSelectedCategoryIndexChange(index)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = category.icon),
                        contentDescription = "Entertainment category",
                        modifier = Modifier.size(22.dp),
                        tint = if (isSelected) colorResource(id = R.color.dark_blue) else Color.Black
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = category.name,
                        color = if (isSelected) colorResource(id = R.color.dark_blue) else Color.Black,
                        fontFamily = Typography.sanFranciscoRounded,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}