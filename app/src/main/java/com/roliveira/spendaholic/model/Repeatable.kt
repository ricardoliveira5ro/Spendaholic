package com.roliveira.spendaholic.model

enum class Repeatable {
    NOT_REPEATABLE,
    DAY,
    WEEK,
    MONTH,
    YEAR;

    override fun toString(): String {
        return when (this) {
            NOT_REPEATABLE -> "Not repeatable"
            DAY -> "Every day"
            WEEK -> "Every week"
            MONTH -> "Every month"
            YEAR -> "Every year"
        }
    }
}