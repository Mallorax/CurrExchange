package com.example.currencyexchange.ui.currencylist

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.currencyexchange.model.CurrencyExchangeModel
import com.example.currencyexchange.repository.CurrencyExchangeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrExchangeViewModel @Inject constructor(private val repo: CurrencyExchangeRepo): ViewModel() {

    val exchangeRatio = LiveDataReactiveStreams.fromPublisher(repo.getCurrencyRatiosFromDate().cachedIn(viewModelScope))


}