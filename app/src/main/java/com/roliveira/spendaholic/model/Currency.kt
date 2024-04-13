package com.roliveira.spendaholic.model

data class Currency (
    val id: String,
    val name: String,
    val symbol: String
) {
    override fun toString(): String {
        return "$name ($symbol)"
    }
}