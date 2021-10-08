package com.biscuitkid.currencyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrencyInfo")
class CurrencyInfo {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "symbol")
    var symbol: String = ""
}