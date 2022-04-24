package com.ankushsoni.randomquotes.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ankushsoni.randomquotes.repository.QuotesRepository
import com.ankushsoni.randomquotes.viewmodel.TestingFavViewModel

class TestingFAvViewModelFactory(private val quotesRepository: QuotesRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestingFavViewModel(quotesRepository) as T
    }

}