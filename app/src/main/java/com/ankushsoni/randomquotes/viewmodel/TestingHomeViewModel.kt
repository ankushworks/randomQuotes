package com.ankushsoni.randomquotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankushsoni.randomquotes.model.ListQuotes
import com.ankushsoni.randomquotes.model.QuotesModel
import com.ankushsoni.randomquotes.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TestingHomeViewModel(private val quotesRepository: QuotesRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quotesRepository.getQuoteFromRetrofit()
            quotesRepository.setQuotesListInToDb(1)
        }
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            quotesRepository.getQuoteFromRetrofit()
        }
    }

    // just need to call it
    fun getQuotesList() : LiveData<List<ListQuotes>>{
        return  quotesRepository.getAllQuotesList()
    }





    val quoteData : LiveData<QuotesModel>
    get() = quotesRepository.quoteData


}