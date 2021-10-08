package com.biscuitkid.currencyapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.biscuitkid.currencyapp.model.CurrencyInfo


@Database(entities = [CurrencyInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            INSTANCE?.let {
                return it
            }

            return synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "CURRENCY_DATABASE"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}