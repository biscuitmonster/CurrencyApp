package com.biscuitkid.currencyapp.database

import android.content.Context
import android.util.Log
import com.biscuitkid.currencyapp.model.CurrencyInfo
import com.biscuitkid.currencyapp.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CurrencyRepo(private val currencyDao: CurrencyDao) {

    fun readFromJson(context: Context) {
        Completable.fromSingle(
            Single.just(context)
                .map {
                    val data = Utils.openAssets(it, "currency.json")
                    val list = Utils.convertCurrencyInfo(data)

                    currencyDao.insertCurrencyList(list).blockingAwait()
                }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Log.d("CurrencyDaoResult", "onComplete")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("CurrencyDaoResult", e.toString())
                    }

                }
            )
    }

    fun selectAllCurrencyInfo(): Single<List<CurrencyInfo>> {
        return currencyDao.selectAllCurrencyInfo()
    }

    fun selectAllCurrencyInfoSortByName(): Single<List<CurrencyInfo>> {
        return currencyDao.selectAllCurrencyInfoSortByName()
    }
}