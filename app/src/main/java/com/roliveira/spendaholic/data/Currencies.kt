package com.roliveira.spendaholic.data

import com.roliveira.spendaholic.model.Currency

object Currencies {
    val currencies = listOf(
        Currency("USD", "Dollar", "$"),
        Currency("EUR", "Euro", "€"),
        Currency("GBP", "Pound", "£"),
        Currency("JPY", "Yen", "¥"),
        Currency("CHF", "Franc", "₣")
    )
}