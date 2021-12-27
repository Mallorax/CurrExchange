package com.example.currencyexchange.repository

import androidx.paging.*
import androidx.paging.rxjava3.flowable
import com.example.currencyexchange.model.CurrencyExchangeModel
import com.example.currencyexchange.model.mapToExchangeRatio
import com.example.currencyexchange.paging.ExchangeRatioPagingSource
import io.reactivex.rxjava3.core.Flowable
import java.util.*
import javax.inject.Inject

class CurrencyExchangeRepoImpl @Inject constructor(
private val pagingSource: ExchangeRatioPagingSource): CurrencyExchangeRepo {

    override fun getCurrencyRatiosFromDate(): Flowable<PagingData<CurrencyExchangeModel>> {
        return Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
                prefetchDistance = 0,
                initialLoadSize = 1
            ),
            pagingSourceFactory = {pagingSource}
        ).flowable.map { t -> t.flatMap { mapToExchangeRatio(it) } }
    }

}