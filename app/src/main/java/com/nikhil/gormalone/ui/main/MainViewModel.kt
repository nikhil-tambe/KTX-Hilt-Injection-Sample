package com.nikhil.gormalone.ui.main

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.nikhil.gormalone.R
import com.nikhil.gormalone.worker.UploadProductsWorker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    private val _booksStatus = MutableLiveData<Any>()
    val booksStatus: LiveData<Any>
        get() = _booksStatus

    private val _productsStatus = MutableLiveData<Any>()
    val productStatus: LiveData<Any>
        get() = _productsStatus

    private val _productsCount = MutableLiveData<Int>()
    val productCount: LiveData<Int>
        get() = _productsCount

    fun getAllStatus() {
        viewModelScope.launch {
            mainRepo.getBooksCount()
                .onEach {
                    Timber.d("getBooksCount $it")
                    val message =
                        if (it > 0) "You have $it books" else R.string.click_books_button_to_download_books_list
                    _booksStatus.postValue(message)
                }.launchIn(viewModelScope)

            mainRepo.getProductsCount()
                .onEach {
                    _productsCount.postValue(it)
                    Timber.d("getProductsCount $it")
                    val message = if (it > 0) {
                        "$it products waiting to be uploaded"
                    } else {
                        "No products for upload"
                    }
                    _productsStatus.postValue(message)
                }.launchIn(viewModelScope)
        }
    }

    fun uploadNewProducts(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<UploadProductsWorker>()
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork(
            "UploadNewProducts",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

}