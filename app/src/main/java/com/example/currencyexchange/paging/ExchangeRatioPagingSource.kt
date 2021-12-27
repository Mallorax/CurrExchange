package com.example.currencyexchange.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.currencyexchange.model.HistoricalRatesResponse
import com.example.currencyexchange.model.mapToExchangeRatio
import com.example.currencyexchange.network.HistoricalRatesEndpoint
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ExchangeRatioPagingSource @Inject constructor(val retrofit: HistoricalRatesEndpoint)
    : RxPagingSource<String, HistoricalRatesResponse>() {

    override fun getRefreshKey(state: PagingState<String, HistoricalRatesResponse>): String? {
        val anchorPos = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPos) ?: return null
        val prevKeyString = anchorPage.prevKey
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        val cal = Calendar.getInstance()
        if (prevKeyString != null){
            cal.time = format.parse(prevKeyString)
            cal.add(Calendar.DAY_OF_MONTH, -1)
            return format.format(cal.time)
        }
        val nextKeyString = anchorPage.nextKey
        if (nextKeyString != null){
            cal.time = format.parse(nextKeyString)
            cal.add(Calendar.DAY_OF_MONTH, 1)
            return format.format(cal.time)
        }

        return null
    }

    override fun loadSingle(params: LoadParams<String>): Single<LoadResult<String, HistoricalRatesResponse>> {
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        val cal = Calendar.getInstance()
        val nextPageNumber = params.key ?: format.format(cal.time)

        return retrofit.getHistoricalRates(nextPageNumber)
            .observeOn(Schedulers.computation())
            .map{response -> toLoadResult(response, nextPageNumber)}
            .onErrorReturn{t -> LoadResult.Error(t)}
    }

    private fun toLoadResult(response: HistoricalRatesResponse, page: String): LoadResult<String, HistoricalRatesResponse> {
        val responseList = mutableListOf<HistoricalRatesResponse>()
        responseList.add(response)
        return LoadResult.Page(
            responseList,
            prevKey = previousKey(page),
            nextKey = nextKey(page),
            LoadResult.Page.COUNT_UNDEFINED,
            LoadResult.Page.COUNT_UNDEFINED

        )
    }

    private fun previousKey(page: String):String?{
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        val cal = Calendar.getInstance()
        val currentDateString = format.format(cal.time)
        cal.time = format.parse(page)!!
        if (page == currentDateString){
            return null
        }else{
            cal.add(Calendar.DAY_OF_MONTH, 1)
            return format.format(cal.time)
        }
    }
    private fun nextKey(page: String):String?{
        val format = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        val cal = Calendar.getInstance()
        cal.time = format.parse(page)!!
        cal.add(Calendar.DAY_OF_MONTH, -1)
        return format.format(cal.time)
    }
}