package com.example.currencyexchange.ui.currencylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchange.databinding.CurrencyItemBinding
import com.example.currencyexchange.databinding.CurrencyItemBindingImpl
import com.example.currencyexchange.databinding.DateItemBinding
import com.example.currencyexchange.databinding.DateItemBindingImpl
import com.example.currencyexchange.model.CurrExchangeRatio
import com.example.currencyexchange.model.CurrencyExchangeDate
import com.example.currencyexchange.model.CurrencyExchangeModel

class CurrExchangeAdapter(private val onClickListener: OnItemClickListener) :
    PagingDataAdapter<CurrencyExchangeModel, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder.itemViewType) {
            0 -> {
                val currencyRatioHolder = holder as CurrencyExchangeRateVH
                holder.binding.root.setOnClickListener {
                    if (item != null) {
                        onClickListener.onItemClick(item as CurrExchangeRatio, it)
                    }
                }
                currencyRatioHolder.bind(item as CurrExchangeRatio)
            }
            else -> {
                val currencyDateHolder = holder as CurrencyExchangeDateVH
                currencyDateHolder.bind(item as CurrencyExchangeDate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                val binding = CurrencyItemBindingImpl.inflate(inflater, parent, false)
                CurrencyExchangeRateVH(binding)
            }
            else -> {
                val binding = DateItemBindingImpl.inflate(inflater, parent, false)
                CurrencyExchangeDateVH(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item is CurrExchangeRatio) {
            0
        } else {
            1
        }
    }

    class CurrencyExchangeDateVH(val binding: DateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currencyExchangeDate: CurrencyExchangeDate) {
            binding.bindedItem = currencyExchangeDate
            binding.executePendingBindings()
        }
    }

    class CurrencyExchangeRateVH(val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currExchangeRatio: CurrExchangeRatio) {
            binding.bindedItem = currExchangeRatio
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CurrencyExchangeModel>() {
        override fun areItemsTheSame(
            oldItem: CurrencyExchangeModel,
            newItem: CurrencyExchangeModel
        ): Boolean {
            return if (oldItem is CurrExchangeRatio && newItem is CurrExchangeRatio) {
                oldItem === newItem
            } else if (oldItem is CurrencyExchangeDate && newItem is CurrencyExchangeDate) {
                oldItem === newItem
            } else {
                false
            }
        }

        override fun areContentsTheSame(
            oldItem: CurrencyExchangeModel,
            newItem: CurrencyExchangeModel
        ): Boolean {
            return if (oldItem is CurrExchangeRatio && newItem is CurrExchangeRatio) {
                oldItem.currency == newItem.currency && oldItem.ratio == newItem.ratio
            } else if (oldItem is CurrencyExchangeDate && newItem is CurrencyExchangeDate) {
                oldItem.date == newItem.date
            } else {
                false
            }
        }
    }

    class OnItemClickListener(val clickListener: (item: CurrExchangeRatio, v: View) -> Unit) {
        fun onItemClick(item: CurrExchangeRatio, v: View) = clickListener(item, v)
    }
}