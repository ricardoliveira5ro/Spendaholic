package com.roliveira.spendaholic

import java.util.Calendar
import java.util.Date

object DummyData {
    private val calendar = Calendar.getInstance()

    val transactions = listOf(
        Transaction("Entertainment", "Netflix", 12.99f, getDate(2024, 2, 5, 22, 0, 33)),
        Transaction("Groceries", null, 45.37f, getDate(2024, 2, 5, 20, 38, 33)),
        Transaction("Utilities", "Electricity", 39.22f, getDate(2024, 2, 5, 20, 0, 33)),
        Transaction("Rent", null, 750.0f, getDate(2024, 2, 5, 14, 34, 33)),
        Transaction("Clothing", null, 133.34f, getDate(2024, 2, 5, 14, 0, 33))
    )

    private fun getDate(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Date {
        calendar.set(year, month, day, hour, minute, second)
        return calendar.time
    }
}

data class Transaction(
    val category: String,
    val subCategory: String?,
    val amount: Float,
    val data: Date
)