package com.example.currencyexchange.repository

import androidx.paging.PagingData
import com.example.currencyexchange.model.CurrencyExchangeDate
import com.example.currencyexchange.model.CurrencyExchangeModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*

interface CurrencyExchangeRepo {

    fun getCurrencyRatiosFromDate(): Flowable<PagingData<CurrencyExchangeModel>>
}