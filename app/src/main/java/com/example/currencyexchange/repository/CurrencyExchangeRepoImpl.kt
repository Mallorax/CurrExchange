package com.example.currencyexchange.repository

import com.example.currencyexchange.model.CurrencyExchangeModel
import com.example.currencyexchange.model.mapToExchangeRatio
import com.example.currencyexchange.network.HistoricalRatesEndpoint
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrencyExchangeRepoImpl @Inject constructor(private val retrofit: HistoricalRatesEndpoint): CurrencyExchangeRepo {

    override fun getCurrencyRatiosFromDate(calendar: Calendar): Single<List<CurrencyExchangeModel>> {
        val dateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        return retrofit.getHistoricalRates(dateFormat.format(calendar.time))
            .observeOn(Schedulers.computation())
            .map { t -> mapToExchangeRatio(t) }
    }
}