package com.roliveira.spendaholic.ui

import androidx.annotation.DrawableRes
import com.roliveira.spendaholic.R

sealed class Screen(val title: String, val route: String, @DrawableRes val icon: Int) {
    data object Dashboard : Screen("Dashboard", "dashboard", R.drawable.dashboard)
    data object Stats : Screen("Stats", "Stats", R.drawable.pie)
    data object Settings : Screen("Settings", "settings", R.drawable.settings)
}

val screensBottomNavigation = listOf(
    Screen.Stats,
    Screen.Dashboard,
    Screen.Settings
)