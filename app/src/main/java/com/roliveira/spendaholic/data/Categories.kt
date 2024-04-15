package com.roliveira.spendaholic.data

import androidx.compose.ui.graphics.Color
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.model.Category

object Categories {
    val defaultCategories = listOf(
        Category(1,"Groceries", R.drawable.grocery_category, Color(0xFFFAD1A2)),
        Category(2,"Travel", R.drawable.travel_category, Color(0xFF679DB8)),
        Category(3,"Utilities", R.drawable.utilities_category, Color(0xFF65CFFF)),
        Category(4,"Clothing", R.drawable.clothing_category, Color(0xFFFFBD81)),
        Category(5,"Education", R.drawable.education_category, Color(0xFF9C9C9C)),
        Category(6,"Gifts", R.drawable.gifts_category, Color(0xFFD9D9FA)),
        Category(7,"Transportation", R.drawable.transportation_category, Color(0xFFB7FFAB)),
        Category(8,"Entertainment", R.drawable.entertainment_category, Color(0xFFF7838C)),
        Category(9,"Healthcare", R.drawable.healthcare_category, Color(0xFFFFA9AE)),
        Category(10,"Dining Out", R.drawable.dinning_out_category, Color(0xFFDDA199)),
        Category(11,"Other", R.drawable.unknown_category_linear, Color(0xFFAFB4C2))
    )

    val defaultCategory = Category(11,"Other", R.drawable.unknown_category_linear, Color(0xFFFFFFFF))

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