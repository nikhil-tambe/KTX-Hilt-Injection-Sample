package com.nikhil.gormalone.ui.books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.gormalone.model.book.Book
import com.nikhil.gormalone.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class BooksViewModel
@ViewModelInject
constructor(private val booksRepo: BooksRepo) : ViewModel() {

    private val _bookListDataState = MutableLiveData<DataState<List<Book>>>()
    val bookListDataState: LiveData<DataState<List<Book>>>
        get() = _bookListDataState

    fun getAllBooks() {
        viewModelScope.launch {
            booksRepo.getAllBooks()
                .onEach {
                    _bookListDataState.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

}
