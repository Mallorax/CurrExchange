package com.example.currencyexchange.model

data class CurrExchangeRatio(
    val currency: String,
    val ratio: Double): CurrencyExchangeModel() {

        fun getFormattedRatio():String{
            return String.format("%.5f", ratio)
        }
}