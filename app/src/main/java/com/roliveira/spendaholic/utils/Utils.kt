package com.roliveira.spendaholic.utils

import androidx.compose.ui.graphics.Color
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.model.Expense
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {
    fun categoryTransactionIcon(id: Int): Int {
        return when (id) {
            1 -> R.drawable.entertainment
            2 -> R.drawable.grocery
            3 -> R.drawable.utilites
            4 -> R.drawable.clothing
            5 -> R.drawable.transportation
            6 -> R.drawable.healthcare
            7 -> R.drawable.education
            8 -> R.drawable.travel
            9 -> R.drawable.dinning_out
            10 -> R.drawable.gifts
            else -> R.drawable.unknown_category
        }
    }

    fun categoryTransactionColor(id: Int): Color {
        return when (id) {
            1 -> Color(0xFFF7838C)
            2 -> Color(0xFFFAD1A2)
            3 -> Color(0xFF65CFFF)
            4 -> Color(0xFFFFBD81)
            5 -> Color(0xFFB7FFAB)
            6 -> Color(0xFFFFA9AE)
            7 -> Color(0xFF9C9C9C)
            8 -> Color(0xFF679DB8)
            9 -> Color(0xFFDDA199)
            10 -> Color(0xFFD9D9FA)
            else -> Color(0xFFAFB4C2)
        }
    }

    fun dateTime(dateString: String, timeString: String): Date {
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateString)
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(timeString)

        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }
        val timeCalendar = Calendar.getInstance()
        if (time != null) {
            timeCalendar.time = time
        }

        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY))
        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE))

        return calendar.time
    }

    fun dateToString(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    fun timeToString(date: Date): String {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(date)
    }

    fun defaultExpense(): Expense {
        return Expense(
            id = -1,
            category = Categories.defaultCategory,
            note = "",
            amount = 0f,
            date = Calendar.getInstance().time
        )
    }

    fun getNextId(expenses: List<Expense>): Int {
        return ((expenses.maxByOrNull { it.id }?.id ?: 0) + 1)
    }
}