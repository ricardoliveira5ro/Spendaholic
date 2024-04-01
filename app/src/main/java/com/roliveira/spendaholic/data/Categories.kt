package com.roliveira.spendaholic.data

import androidx.compose.ui.graphics.Color
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.model.Category

object Categories {
    val defaultCategories = listOf(
        Category(1,"Groceries", R.drawable.grocery_category, Color(0xFFFFFFFF)),
        Category(2,"Travel", R.drawable.travel_category, Color(0xFFFFFFFF)),
        Category(3,"Utilities", R.drawable.utilities_category, Color(0xFFFFFFFF)),
        Category(4,"Clothing", R.drawable.clothing_category, Color(0xFFFFFFFF)),
        Category(5,"Education", R.drawable.education_category, Color(0xFFFFFFFF)),
        Category(6,"Gifts", R.drawable.gifts_category, Color(0xFFFFFFFF)),
        Category(7,"Transportation", R.drawable.transportation_category, Color(0xFFFFFFFF)),
        Category(8,"Entertainment", R.drawable.entertainment_category, Color(0xFFFFFFFF)),
        Category(9,"Dining Out", R.drawable.dinning_out_category, Color(0xFFFFFFFF)),
        Category(10,"Groceries", R.drawable.grocery_category, Color(0xFFFFFFFF)),
        Category(11,"Other", R.drawable.unknown_category_linear, Color(0xFFFFFFFF))
    )

    val defaultCategory = Category(11,"Other", R.drawable.unknown_category_linear, Color(0xFFFFFFFF))
}