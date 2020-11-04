package com.nikhil.gormalone.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.gormalone.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    private val _booksStatus = MutableLiveData<Any>().apply {
        this.postValue(R.string.click_books_button_to_download_books_list)
    }
    val booksStatus: LiveData<Any>
        get() = _booksStatus

    private val _productsStatus = MutableLiveData<Any>()
    val productStatus: LiveData<Any>
        get() = _productsStatus

    fun getAllStatus() {
        viewModelScope.launch {
            mainRepo.getBooksCount()
                .onEach {
                    val message =
                        if (it > 0) "You have $it books" else R.string.click_books_button_to_download_books_list
                    _booksStatus.postValue(message)
                }.launchIn(viewModelScope)

            mainRepo.getProductsCount()
                .onEach {
                    val message = if (it > 0) {
                        "$it products waiting to be uploaded"
                    } else {
                        "No products for upload"
                    }
                    _productsStatus.postValue(message)
                }.launchIn(viewModelScope)
        }
    }

}