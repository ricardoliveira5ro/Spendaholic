package com.roliveira.spendaholic.ui

import androidx.annotation.DrawableRes
import com.roliveira.spendaholic.R

sealed class Screen(val title: String, val route: String, @DrawableRes val icon: Int) {
    object Dashboard: Screen("Dashboard", "dashboard", R.drawable.dashboard)
}

val screens = listOf(
    Screen.Dashboard
)