package com.example.currencyexchange.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.currencyexchange.databinding.CurrExchangeListFragmentBinding
import com.example.currencyexchange.network.HistoricalRatesEndpoint
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@AndroidEntryPoint
class CurrExchangeListFragment: Fragment() {

    private var _binding: CurrExchangeListFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrExchangeViewModel by viewModels()

    @Inject lateinit var service: HistoricalRatesEndpoint


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CurrExchangeListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.exchangeRatio.observe(this.viewLifecycleOwner, Observer {

        })



        return binding.root
    }
}