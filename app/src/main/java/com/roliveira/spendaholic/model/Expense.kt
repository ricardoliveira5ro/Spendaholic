package com.roliveira.spendaholic.model

import java.util.Date

data class Expense (
    val id: Int,
    val category: Category,
    val note: String?,
    val amount: Float,
    val date: Date,
    val repeatable: Repeatable,
    val isWorkRepeatable: Boolean = false
)