package com.biscuitkid.currencyapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.biscuitkid.currencyapp.databinding.CurrencyItemBinding
import com.biscuitkid.currencyapp.model.CurrencyInfo

class BindableViewHolder(private val binding: CurrencyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CurrencyInfo, listener: CurrencyAdapter.CurrencyItemClickListener) {
        binding.model = item
        binding.itemClick = listener
    }
}