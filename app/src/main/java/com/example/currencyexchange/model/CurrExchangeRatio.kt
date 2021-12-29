package com.example.currencyexchange.model

data class CurrExchangeRatio(
    val currency: String,
    val ratio: Double,
    val date: String,
    val defaultCurrency: String): CurrencyExchangeModel() {

        fun getFormattedRatio():String{
            return String.format("%.5f", ratio)
        }
}