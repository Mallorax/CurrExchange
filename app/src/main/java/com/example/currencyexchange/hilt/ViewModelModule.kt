package com.example.currencyexchange.hilt

import com.example.currencyexchange.network.HistoricalRatesEndpoint
import com.example.currencyexchange.paging.ExchangeRatioPagingSource
import com.example.currencyexchange.repository.CurrencyExchangeRepo
import com.example.currencyexchange.repository.CurrencyExchangeRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun currExchangeRepo(repoImpl: CurrencyExchangeRepoImpl): CurrencyExchangeRepo

    @Inject
    @Singleton
    @ViewModelScoped
    fun provideCurrExchangeRepo(pagingSource: ExchangeRatioPagingSource): CurrencyExchangeRepo{
        return CurrencyExchangeRepoImpl(pagingSource)
    }
}