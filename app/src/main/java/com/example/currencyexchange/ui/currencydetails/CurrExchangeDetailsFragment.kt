package com.example.currencyexchange.ui.currencydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.currencyexchange.databinding.CurrExchangeDetailsFragmentBinding

class CurrExchangeDetailsFragment: Fragment() {

    private var _binding: CurrExchangeDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: CurrExchangeDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CurrExchangeDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.bindedItem = args.currExchangeRatio

        return binding.root
    }
}