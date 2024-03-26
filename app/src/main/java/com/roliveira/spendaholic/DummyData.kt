package com.roliveira.spendaholic

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DummyData {
    private val calendar = Calendar.getInstance()

    private val defaultCategories = listOf(
        CategoryDummy("Entertainment", R.drawable.entertainment, Color(0xFFF7838C)),
        CategoryDummy("Groceries", R.drawable.grocery, Color(0xFFFAD1A2)),
        CategoryDummy("Other", R.drawable.unknown_category, Color(0xFFAFB4C2)),
        CategoryDummy("Utilities", R.drawable.utilites, Color(0xFF65CFFF)),
        CategoryDummy("Clothing", R.drawable.clothing, Color(0xFFFFBD81)),
        CategoryDummy("Transportation", R.drawable.transportation, Color(0xFFB7FFAB)),
        CategoryDummy("Healthcare", R.drawable.healthcare, Color(0xFFFFA9AE)),
        CategoryDummy("Education", R.drawable.education, Color(0xFF9C9C9C)),
        CategoryDummy("Travel", R.drawable.travel, Color(0xFF679DB8)),
        CategoryDummy("Dining Out", R.drawable.dinning_out, Color(0xFFDDA199)),
        CategoryDummy("Gifts", R.drawable.gifts, Color(0xFFD9D9FA))
    )

    val transactions = listOf(
        Transaction(defaultCategories[0], "Netflix", 12.99f, getDate(2024, 2, 5, 22, 0, 33)),
        Transaction(defaultCategories[1], null, 45.37f, getDate(2024, 2, 5, 20, 38, 33)),
        Transaction(defaultCategories[2], null, 39.22f, getDate(2024, 2, 5, 20, 0, 33)),
        Transaction(defaultCategories[3], "Electricity", 133.34f, getDate(2024, 2, 5, 14, 34, 33)),
        Transaction(defaultCategories[4], null, 750.00f, getDate(2024, 2, 5, 14, 0, 33)),
        Transaction(defaultCategories[5], "Fuel", 40.00f, getDate(2024, 2, 5, 13, 54, 33)),
        Transaction(defaultCategories[6], null, 54.44f, getDate(2024, 2, 5, 13, 44, 33)),
        Transaction(defaultCategories[7], "Tuition fee", 220.00f, getDate(2024, 2, 1, 20, 38, 33)),
        Transaction(defaultCategories[8], "Madrid", 350.34f, getDate(2024, 2, 1, 19, 0, 33)),
        Transaction(defaultCategories[9], "Burger King", 5.90f, getDate(2023, 12, 29, 14, 34, 33)),
        Transaction(defaultCategories[10], null, 120.00f, getDate(2023, 12, 27, 10, 0, 34)),
    )

    private fun getDate(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Date {
        calendar.set(year, month, day, hour, minute, second)
        return calendar.time
    }
}

data class Transaction(
    val category: CategoryDummy,
    val subCategory: String?,
    val amount: Float,
    val date: Date
) {
    fun formatDate(): String {
        val format = SimpleDateFormat("MMM d', 'HH:mm", Locale.getDefault())
        return format.format(date)
    }
}

data class CategoryDummy (
    val name: String,
    @DrawableRes val icon: Int,
    val backgroundColor: Color
)