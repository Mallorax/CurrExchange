package com.example.currencyexchange.model

import java.text.SimpleDateFormat
import java.util.*

data class CurrencyExchangeDate(
    val date: Calendar
): CurrencyExchangeModel() {

    fun getDateInString():String{
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return format.format(date.time)
    }
}