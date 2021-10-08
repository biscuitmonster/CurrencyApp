package com.biscuitkid.currencyapp

import android.app.Application
import com.biscuitkid.currencyapp.database.CurrencyRepo
import com.biscuitkid.currencyapp.di.databaseModule
import com.biscuitkid.currencyapp.di.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    private val currencyRepo: CurrencyRepo by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(databaseModule, viewModelModule))
        }

        currencyRepo.readFromJson(applicationContext)
    }
}