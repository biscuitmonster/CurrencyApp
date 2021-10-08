package com.biscuitkid.currencyapp.ui.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.biscuitkid.currencyapp.viewModel.CurrencyViewModel
import com.biscuitkid.currencyapp.R
import com.biscuitkid.currencyapp.databinding.DemoActivityBinding
import com.biscuitkid.currencyapp.ui.currency.CurrencyListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : AppCompatActivity() {

    private val vm by viewModel<CurrencyViewModel>()
    private lateinit var activityDemoActivityBinding: DemoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDemoActivityBinding = DataBindingUtil.setContentView(this, R.layout.demo_activity)
        activityDemoActivityBinding.viewModel = vm

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrencyListFragment.newInstance())
                .commitNow()
        }
    }
}