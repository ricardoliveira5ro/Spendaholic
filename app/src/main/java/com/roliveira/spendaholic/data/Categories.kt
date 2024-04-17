package com.roliveira.spendaholic.data

import androidx.compose.ui.graphics.Color
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.model.Category

object Categories {
    val defaultCategories = listOf(
        Category(1,"Groceries", R.drawable.grocery, Color(0xFFFAD1A2)),
        Category(2,"Travel", R.drawable.travel, Color(0xFF679DB8)),
        Category(3,"Utilities", R.drawable.utilites, Color(0xFF65CFFF)),
        Category(4,"Clothing", R.drawable.clothing, Color(0xFFFFBD81)),
        Category(5,"Education", R.drawable.education, Color(0xFF9C9C9C)),
        Category(6,"Gifts", R.drawable.gifts, Color(0xFFD9D9FA)),
        Category(7,"Transportation", R.drawable.transportation, Color(0xFFB7FFAB)),
        Category(8,"Entertainment", R.drawable.entertainment, Color(0xFFF7838C)),
        Category(9,"Healthcare", R.drawable.healthcare, Color(0xFFFFA9AE)),
        Category(10,"Dining Out", R.drawable.dinning_out, Color(0xFFDDA199)),
        Category(11,"Other", R.drawable.unknown_category, Color(0xFFAFB4C2))
    )

    val defaultCategory = Category(11,"Other", R.drawable.unknown_category, Color(0xFFAFB4C2))

    val categoriesIcons = listOf(
        R.drawable.grocery,
        R.drawable.travel,
        R.drawable.utilites,
        R.drawable.clothing,
        R.drawable.education,
        R.drawable.gifts,
        R.drawable.transportation,
        R.drawable.entertainment,
        R.drawable.healthcare,
        R.drawable.dinning_out,
        R.drawable.nightout,
        R.drawable.personalcare,
        R.drawable.pets,
        R.drawable.savings,
        R.drawable.sports,
        R.drawable.unknown_category
    )
}