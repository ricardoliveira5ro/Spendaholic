package com.roliveira.spendaholic.ui.screens.settings.categories

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@Composable
fun ManageCategories(
    categories: List<Category>,
    onNavigateBack: () -> Unit,
    onNewCategory: () -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                text = "Categories",
                color = Color.Black,
                fontFamily = Typography.sanFranciscoText,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            IconButton(
                onClick = { onNewCategory() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_vector),
                    contentDescription = "Add expense",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, colorResource(id = R.color.light_grey), RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = category.icon), contentDescription = category.name)

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = category.name,
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }

        IconButton(onClick = {  }) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete Category",
                tint = Color.Red,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManageCategoriesPreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ManageCategories(categories = Categories.defaultCategories, {}, {})
        }
    }
}