package com.roliveira.spendaholic.data

import com.roliveira.spendaholic.model.Repeatable

object Repeatables {
    val repeatOptions = listOf(
        Repeatable.NOT_REPEATABLE,
        Repeatable.DAY,
        Repeatable.WEEK,
        Repeatable.MONTH,
        Repeatable.YEAR,
    )
}