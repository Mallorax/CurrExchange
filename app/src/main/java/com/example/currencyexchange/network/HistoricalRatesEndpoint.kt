package com.example.currencyexchange.network


import com.example.currencyexchange.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HistoricalRatesEndpoint {

    @GET("{date}")
    fun getHistoricalRates(@Path("date")date: String = "2021-12-27",
                           @Query("access_key") apiKey: String = BuildConfig.API_KEY)
}