package com.ankushsoni.randomquotes.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.ankushsoni.randomquotes.model.QuotesDatabase
import com.ankushsoni.randomquotes.model.QuotesModel
import com.ankushsoni.randomquotes.model.QuotesTable
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    var allQuotes : MutableLiveData<List<QuotesTable>> = MutableLiveData()

    init {
        getAllQuotes()
    }

    fun getAllUserObservers() : MutableLiveData<List<QuotesTable>> {
        return allQuotes
    }

    fun getAllQuotes(){
            val userDao = QuotesDatabase.getDatabase(getApplication())?.quotesDao()
            val list = userDao.getQuotes()
//            Log.d("QuotesData" , list.toString())
//            allQuotes.postValue(list)

    }

    suspend fun deleteQuote(quoteData : QuotesTable){
        val userDao = QuotesDatabase.getDatabase(getApplication())?.quotesDao()
        userDao.deleteQuotes(quoteData)
        getAllQuotes()
    }

}