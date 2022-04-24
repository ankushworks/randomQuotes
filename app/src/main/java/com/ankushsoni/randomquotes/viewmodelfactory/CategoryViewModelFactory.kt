package com.ankushsoni.randomquotes.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ankushsoni.randomquotes.repository.QuotesRepository
import com.ankushsoni.randomquotes.viewmodel.CategoryViewModel

class CategoryViewModelFactory(val quotesRepository: QuotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(quotesRepository) as T
    }
}