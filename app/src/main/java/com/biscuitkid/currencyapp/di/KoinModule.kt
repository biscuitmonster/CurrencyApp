package com.biscuitkid.currencyapp.di

import com.biscuitkid.currencyapp.database.AppDatabase
import com.biscuitkid.currencyapp.database.CurrencyRepo
import com.biscuitkid.currencyapp.viewModel.CurrencyViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { CurrencyViewModel(get()) }
}

val databaseModule = module {
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().currencyDao() }
    single { CurrencyRepo(get()) }
}