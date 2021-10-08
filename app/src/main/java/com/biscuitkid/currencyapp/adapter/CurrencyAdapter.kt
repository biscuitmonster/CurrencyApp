package com.biscuitkid.currencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.biscuitkid.currencyapp.R
import com.biscuitkid.currencyapp.databinding.CurrencyItemBinding
import com.biscuitkid.currencyapp.model.CurrencyInfo
import java.util.ArrayList

class CurrencyAdapter(private val listener: CurrencyItemClickListener) :
    RecyclerView.Adapter<BindableViewHolder>() {
    var items: ArrayList<CurrencyInfo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding: CurrencyItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.currency_item,
            parent,
            false
        )

        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(list: List<CurrencyInfo>?) {
        items.clear()
        list?.let { items.addAll(it) }
        notifyDataSetChanged()
    }

    interface CurrencyItemClickListener {
        fun onCurrencyItemClicked(item: CurrencyInfo)
    }

}
