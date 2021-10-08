package com.biscuitkid.currencyapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.biscuitkid.currencyapp.model.CurrencyInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyList(list: List<CurrencyInfo>): Completable

    @Query("SELECT * FROM CurrencyInfo")
    fun selectAllCurrencyInfo(): Single<List<CurrencyInfo>>

    @Query("SELECT * FROM CurrencyInfo ORDER BY name")
    fun selectAllCurrencyInfoSortByName(): Single<List<CurrencyInfo>>
}