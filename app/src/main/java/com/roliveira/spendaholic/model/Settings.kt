package com.roliveira.spendaholic.model

data class Settings (
    val currency: Currency,
    val categories: List<Category>
)