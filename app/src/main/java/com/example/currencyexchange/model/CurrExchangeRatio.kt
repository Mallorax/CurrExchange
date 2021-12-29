package com.example.currencyexchange.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrExchangeRatio(
    val currency: String,
    val ratio: Double,
    val date: String,
    val defaultCurrency: String): CurrencyExchangeModel(), Parcelable {

        fun getFormattedRatio():String{
            return String.format("%.5f", ratio)
        }
}