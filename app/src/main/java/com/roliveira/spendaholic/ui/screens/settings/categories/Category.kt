package com.roliveira.spendaholic.ui.screens.settings.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.fonts.Typography
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.ui.theme.SpendaholicTheme

@Composable
fun Category(
    category: Category
) {
    var nameState by remember { mutableStateOf("") }
    var selectedIconIndex by rememberSaveable { mutableIntStateOf(0) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryHeader()

        CategoryNameSection(
            nameState = nameState,
            onNameChange = { nameState = it }
        )

        CategoryIconSection(
            selectedIconIndex = selectedIconIndex,
            onSelectedIconIndexChange = { selectedIconIndex = it }
        )
    }
}

@Composable
fun CategoryHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {  }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Go back",
                tint = Color.Black
            )
        }

        Text(
            text = "Category",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        IconButton(
            onClick = {  }
        ) {
            Image(
                painter = painterResource(id = R.drawable.save),
                contentDescription = "Go back",
                modifier = Modifier.size(30.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun CategoryNameSection(
    nameState: String,
    onNameChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Name",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        TextField(
            value = nameState,
            onValueChange = { onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = Typography.sanFranciscoRounded,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
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

    Spacer(modifier = Modifier.height(8.dp))
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryIconSection(
    selectedIconIndex: Int,
    onSelectedIconIndexChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Icon",
            color = Color.Black,
            fontFamily = Typography.sanFranciscoRounded,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        FlowRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Categories.categoriesIcons.onEachIndexed { index, icon ->
                val isSelected = selectedIconIndex == index

                Row (
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 4.dp)
                        .border(
                            width = if (isSelected) 1.dp else 0.dp,
                            color = if (isSelected) colorResource(id = R.color.dark_blue)
                            else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onSelectedIconIndexChange(index)
                        },
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(65.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    SpendaholicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Category(category = Categories.defaultCategories[0])
        }
    }
}