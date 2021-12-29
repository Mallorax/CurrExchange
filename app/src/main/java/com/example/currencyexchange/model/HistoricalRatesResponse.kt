package com.example.currencyexchange.model

data class HistoricalRatesResponse(
    val base: String?,
    val date: String?,
    val historical: Boolean,
    val rates: Map<String, Double>?,
    val success: Boolean?,
    val timestamp: Int?
)