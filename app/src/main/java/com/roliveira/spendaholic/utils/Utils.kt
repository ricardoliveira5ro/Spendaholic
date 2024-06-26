package com.roliveira.spendaholic.utils

import androidx.compose.ui.graphics.Color
import com.roliveira.spendaholic.R
import com.roliveira.spendaholic.data.Categories
import com.roliveira.spendaholic.data.Currencies
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Currency
import com.roliveira.spendaholic.model.Expense
import com.roliveira.spendaholic.model.Repeatable
import com.roliveira.spendaholic.model.Settings
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {
    fun categoryTransactionIcon(id: Int): Int {
        return when (id) {
            1 -> R.drawable.grocery
            2 -> R.drawable.travel
            3 -> R.drawable.utilites
            4 -> R.drawable.clothing
            5 -> R.drawable.education
            6 -> R.drawable.gifts
            7 -> R.drawable.transportation
            8 -> R.drawable.entertainment
            9 -> R.drawable.healthcare
            10 -> R.drawable.dinning_out
            11 -> R.drawable.unknown_category
            else -> R.drawable.unknown_category
        }
    }

    fun categoryTransactionColor(id: Int): Color {
        return when (id) {
            1 -> Color(0xFFFAD1A2)
            2 -> Color(0xFF679DB8)
            3 -> Color(0xFF65CFFF)
            4 -> Color(0xFFFFBD81)
            5 -> Color(0xFF9C9C9C)
            6 -> Color(0xFFD9D9FA)
            7 -> Color(0xFFB7FFAB)
            8 -> Color(0xFFF7838C)
            9 -> Color(0xFFFFA9AE)
            10 -> Color(0xFFDDA199)
            11 -> Color(0xFFAFB4C2)
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

    fun dateTimeDashboardFormat(date: Date): String {
        val format = SimpleDateFormat("MMM d', 'HH:mm", Locale.getDefault())
        return format.format(date)
    }

    fun defaultExpense(): Expense {
        return Expense(
            id = -1,
            category = Categories.defaultCategory,
            note = "",
            amount = 0f,
            date = Calendar.getInstance().time,
            repeatable = Repeatable.NOT_REPEATABLE
        )
    }

    fun getNextId(expenses: List<Expense>): Int {
        return ((expenses.maxByOrNull { it.id }?.id ?: 0) + 1)
    }

    fun getNextCategoryId(categories: List<Category>): Int {
        return ((categories.maxByOrNull { it.id }?.id ?: 0) + 1)
    }

    fun formatFloatWithTwoDecimalPlaces(number: Float): String {
        return String.format("%.2f", number)
    }

    fun calculateNextExpenseDate(expense: Expense): String {
        val currentDate = Calendar.getInstance()
        val expenseDate = Calendar.getInstance().apply { time = expense.date }

        val format = SimpleDateFormat("MMM d', 'HH:mm", Locale.getDefault())

        return when (expense.repeatable) {
            Repeatable.WEEK -> {
                val daysToAdd = (Calendar.SATURDAY - expenseDate.get(Calendar.DAY_OF_WEEK) + 1) % 7
                expenseDate.add(Calendar.DAY_OF_MONTH, daysToAdd)

                // Skip today's occurrence
                if (currentDate.get(Calendar.DAY_OF_WEEK) == expenseDate.get(Calendar.DAY_OF_WEEK)) {
                    expenseDate.add(Calendar.DAY_OF_MONTH, 7)
                }

                format.format(expenseDate.time)
            }

            Repeatable.MONTH -> {
                val currentMonth = currentDate.get(Calendar.MONTH)
                val expenseMonth = expenseDate.get(Calendar.MONTH)

                if (currentMonth == expenseMonth) expenseDate.add(Calendar.MONTH, 1)

                format.format(expenseDate.time)
            }

            else -> format.format(expenseDate.time)
        }
    }

    fun validateSettings(settings: Settings?) : Settings {
        if (settings?.currency?.id != null && settings.categories.isNotEmpty()) {
            return settings
        }

        return Settings(Currencies.currencies[0], Categories.defaultCategories)
    }

    fun defaultCategory(): Category {
        return Category(
            id = -1,
            name = "",
            icon = R.drawable.grocery,
            backgroundColor = Color.White
        )
    }

    fun categoryIconMapper(iconId: Int): Int {
        return when(iconId) {
            R.drawable.grocery -> R.drawable.grocery_category
            R.drawable.travel -> R.drawable.travel_category
            R.drawable.utilites -> R.drawable.utilities_category
            R.drawable.clothing -> R.drawable.clothing_category
            R.drawable.education -> R.drawable.education_category
            R.drawable.gifts -> R.drawable.gifts_category
            R.drawable.transportation -> R.drawable.transportation_category
            R.drawable.entertainment -> R.drawable.entertainment_category
            R.drawable.healthcare -> R.drawable.healthcare_category
            R.drawable.dinning_out -> R.drawable.dinning_out_category
            R.drawable.nightout -> R.drawable.nightout_category
            R.drawable.personalcare -> R.drawable.personalcare_category
            R.drawable.pets -> R.drawable.pets_category
            R.drawable.savings -> R.drawable.savings_category
            R.drawable.sports -> R.drawable.sports_category
            R.drawable.unknown_category -> R.drawable.unknown_category_linear
            else -> R.drawable.unknown_category_linear
        }
    }

    fun currencyIconMapper(currency: Currency): Int {
        return when (currency.name) {
            "Euro" -> R.drawable.euro
            "Dollar" -> R.drawable.dollar
            "Pound" -> R.drawable.pound
            "Yen" -> R.drawable.yen
            "Franc" -> R.drawable.franc
            else -> R.drawable.dollar
        }
    }
}