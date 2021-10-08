package com.biscuitkid.currencyapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biscuitkid.currencyapp.database.CurrencyRepo
import com.biscuitkid.currencyapp.model.CurrencyInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class CurrencyViewModel(private val currencyRepo: CurrencyRepo) : ViewModel() {

    val currencyInfoList = MutableLiveData<List<CurrencyInfo>>()
    private var isLoading = false

    fun onLoad() {
        currencyRepo.selectAllCurrencyInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<CurrencyInfo>> {
                override fun onSuccess(t: List<CurrencyInfo>) {
                    currencyInfoList.value = t
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d("CurrencyDaoResult", e.toString())
                }

            })
    }

    fun onSort() {
        if(isLoading) {
            return
        }

        currencyRepo.selectAllCurrencyInfoSortByName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<CurrencyInfo>> {
                override fun onSuccess(t: List<CurrencyInfo>) {
                    currencyInfoList.value = t
                    isLoading = false
                }

                override fun onSubscribe(d: Disposable) {
                    isLoading = true
                }

                override fun onError(e: Throwable) {
                    isLoading = false
                }

            })

    }

}

