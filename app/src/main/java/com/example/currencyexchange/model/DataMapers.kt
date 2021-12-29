package com.example.currencyexchange.model

import java.text.SimpleDateFormat
import java.util.*

fun mapToExchangeRatio(response: HistoricalRatesResponse): List<CurrencyExchangeModel>{
    val result = mutableListOf<CurrencyExchangeModel>()
    if (response.success != true){
        return result
    }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val cal = Calendar.getInstance()
    cal.time = dateFormat.parse(response.date)!!
    result.add(CurrencyExchangeDate(cal))
    response.rates?.forEach{(key, value) ->
        result.add(CurrExchangeRatio(key, value, response.date.orEmpty(), response.base.orEmpty()))
    }
    return result
}