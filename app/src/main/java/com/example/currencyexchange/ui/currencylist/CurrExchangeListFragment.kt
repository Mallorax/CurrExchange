package com.example.currencyexchange.ui.currencylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.currencyexchange.databinding.CurrExchangeListFragmentBinding
import com.example.currencyexchange.network.HistoricalRatesEndpoint
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
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

        val adapter = setupRecyclerViewAdapter()
        val recyclerView = binding.currExchangeListRecycler
        recyclerView.adapter = adapter

        viewModel.exchangeRatio.observe(this.viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })



        return binding.root
    }

    private fun setupRecyclerViewAdapter(): CurrExchangeAdapter {
        return CurrExchangeAdapter(CurrExchangeAdapter.OnItemClickListener { item, view ->
            Snackbar.make(view, "Item clicked", Snackbar.LENGTH_LONG).show()
        })
    }
}