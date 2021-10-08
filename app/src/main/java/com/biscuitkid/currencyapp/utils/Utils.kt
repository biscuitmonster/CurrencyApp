package com.biscuitkid.currencyapp.utils

import android.content.Context
import com.biscuitkid.currencyapp.model.CurrencyInfo
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringWriter

object Utils {
    @Throws(IOException::class)
    fun openAssets(context: Context,
                   filename: String): String {


        val inputStream = context.assets.open(filename)
        val inputStreamReader = InputStreamReader(inputStream, Charsets.UTF_8)
        val bufferedReader = BufferedReader(inputStreamReader, DEFAULT_BUFFER_SIZE)
        val bufferWriter = StringWriter()

        var charsCopied: Long = 0
        val buffer = CharArray(DEFAULT_BUFFER_SIZE)
        var chars = bufferedReader.read(buffer)
        while (chars >= 0) {
            bufferWriter.write(buffer, 0, chars)
            charsCopied += chars
            chars = bufferedReader.read(buffer)
        }

        return bufferWriter.toString()
    }

    fun convertCurrencyInfo(result: String): List<CurrencyInfo> {

        val gson = GsonBuilder().create()
        return gson.fromJson(result , Array<CurrencyInfo>::class.java).toList()
    }
}