package com.roliveira.spendaholic.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Category (
    val id: Int,
    val name: String,
    @DrawableRes val icon: Int,
    val backgroundColor: Color,
)