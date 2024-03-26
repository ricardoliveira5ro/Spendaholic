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
        CategoryDummy("Groceries", R.drawable.grocery, Color(0xFFFAD1A2))
    )

    val transactions = listOf(
        Transaction(defaultCategories[0], "Entertainment", 12.99f, getDate(2024, 2, 5, 22, 0, 33)),
        Transaction(defaultCategories[1], null, 45.37f, getDate(2024, 2, 5, 20, 38, 33)),
        //Transaction("Utilities", "Electricity", 39.22f, getDate(2024, 2, 5, 20, 0, 33)),
        //Transaction("Rent", null, 750.0f, getDate(2024, 2, 5, 14, 34, 33)),
        //Transaction("Clothing", null, 133.34f, getDate(2024, 2, 5, 14, 0, 33))
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