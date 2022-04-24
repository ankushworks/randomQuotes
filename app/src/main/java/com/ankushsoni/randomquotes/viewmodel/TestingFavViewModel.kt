package com.ankushsoni.randomquotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankushsoni.randomquotes.model.QuotesTable
import com.ankushsoni.randomquotes.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestingFavViewModel(private val quotesRepository: QuotesRepository) : ViewModel() {


    fun getQuotes() : LiveData<List<QuotesTable>>{
        return quotesRepository.getQuotes()
    }

    fun insertQuote(quote : QuotesTable){
        viewModelScope.launch(Dispatchers.IO) {
            quotesRepository.insertQuote(quote)
        }
    }

    fun deleteQuote(quote : QuotesTable){
        viewModelScope.launch(Dispatchers.IO) {
            quotesRepository.deleteQuote(quote)
        }
    }



}