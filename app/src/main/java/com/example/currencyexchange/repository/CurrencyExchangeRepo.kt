package com.example.currencyexchange.repository

import com.example.currencyexchange.model.CurrencyExchangeDate
import com.example.currencyexchange.model.CurrencyExchangeModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*

interface CurrencyExchangeRepo {

    fun getCurrencyRatiosFromDate(calendar: Calendar): Single<List<CurrencyExchangeModel>>
}