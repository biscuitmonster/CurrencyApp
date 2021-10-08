package com.biscuitkid.currencyapp.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.biscuitkid.currencyapp.R
import com.biscuitkid.currencyapp.databinding.CurrencyListFragmentBinding
import com.biscuitkid.currencyapp.model.CurrencyInfo
import com.biscuitkid.currencyapp.viewModel.CurrencyViewModel
import com.biscuitkid.currencyapp.adapter.CurrencyAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyListFragment : Fragment(), CurrencyAdapter.CurrencyItemClickListener {

    private val vm by viewModel<CurrencyViewModel>()
    private val _adapter = CurrencyAdapter(this)
    private lateinit var fragmentCurrencyListFragmentBinding: CurrencyListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCurrencyListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.currency_list_fragment, container, false)
        fragmentCurrencyListFragmentBinding.viewModel = vm
        fragmentCurrencyListFragmentBinding.recyclerView.adapter = _adapter

        vm.currencyInfoList.observe(viewLifecycleOwner,{
            _adapter.updateItems(it)
        })

        return fragmentCurrencyListFragmentBinding.root
    }

    override fun onCurrencyItemClicked(item: CurrencyInfo) {
        Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = CurrencyListFragment()
    }


}