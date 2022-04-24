package com.ankushsoni.randomquotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankushsoni.randomquotes.model.QuotesModel
import com.ankushsoni.randomquotes.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val quotesRepository: QuotesRepository) : ViewModel(){


//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            quotesRepository.getQuotesCategoryFromRetrofit()
//        }
//    }
//
//    fun makeApiCallForCategory(){
//        viewModelScope.launch(Dispatchers.IO) {
//            quotesRepository.getQuotesCategoryFromRetrofit()
//        }
//    }
//
//    val quoteCategoryData : LiveData<QuotesModel>
//    get() = quotesRepository.quoteCateData

}