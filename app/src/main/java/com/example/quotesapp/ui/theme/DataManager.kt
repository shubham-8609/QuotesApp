package com.example.quotesapp.ui.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotesapp.models.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataManager {

    var data = arrayListOf<Quote>()
    var isDataLoaded = mutableStateOf(false)
    fun loadAssetsFromFile(context: Context): List<Quote> {
        val inputStream = context.assets.open("Quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val type = object : TypeToken<List<Quote>>() {}.type
        data = gson.fromJson(json, type)
        inputStream.close()
        isDataLoaded.value = true
        return data
    }


}