package com.example.currencyexchange.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.currencyexchange.model.CurrencyExchangeModel
import com.example.currencyexchange.repository.CurrencyExchangeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrExchangeViewModel @Inject constructor(private val repo: CurrencyExchangeRepo): ViewModel() {

    val exchangeRatio = LiveDataReactiveStreams.fromPublisher(repo.getCurrencyRatiosFromDate())


}