package com.example.currencyexchange.ui.currencydetails

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.currencyexchange.model.CurrExchangeRatio

//getting string from strings file via getString(),
// didn't work for some reason, so I've decided to hardcode it

@BindingAdapter("dateLabel")
fun bindDateLabel(textView: TextView, item: CurrExchangeRatio){
    textView.text = "Currency exchange rate from: ${item.date}"
}

@BindingAdapter("exchangeRate")
fun bindExchangeRate(textView: TextView, item: CurrExchangeRatio){
    textView.text = "1 ${item.defaultCurrency} equals to ${item.getFormattedRatio()} ${item.currency}"
}