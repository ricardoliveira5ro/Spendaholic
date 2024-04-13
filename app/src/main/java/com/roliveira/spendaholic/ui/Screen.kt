package com.roliveira.spendaholic.ui

import androidx.annotation.DrawableRes
import com.roliveira.spendaholic.R

sealed class Screen(val title: String, val route: String, @DrawableRes val icon: Int) {
    data object Dashboard : Screen("Dashboard", "dashboard", R.drawable.dashboard)
    data object Stats : Screen("Stats", "stats", R.drawable.pie)
    data object Settings : Screen("Settings", "settings", R.drawable.settings)
    data object Expense : Screen("Expense", "expense", R.drawable.add)
    data object Schedule : Screen("Schedule", "schedule", R.drawable.schedule)
    data object Summary : Screen("Summary", "summary", R.drawable.summary)
    data object ManageCategories : Screen("Manage Categories", "manage-categories", R.drawable.settings)
}

val screensBottomNavigation = listOf(
    Screen.Stats,
    Screen.Dashboard,
    Screen.Settings
)