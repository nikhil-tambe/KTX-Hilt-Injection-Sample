package com.nikhil.gormalone.ui.books

import com.nikhil.gormalone.db.books.BooksDao
import com.nikhil.gormalone.model.book.Book
import com.nikhil.gormalone.model.book.BooksMapper
import com.nikhil.gormalone.network.BooksApi
import com.nikhil.gormalone.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksRepo
constructor(
    private val booksApi: BooksApi,
    private val booksDao: BooksDao,
    private val booksMapper: BooksMapper
) {

    var allBooks: List<Book> = ArrayList()

    suspend fun getAllBooks(): Flow<DataState<List<Book>>> = flow {
        emit(DataState.Loading)
        delay(300)

        val books = booksDao.getAllBooks()
        if (books.isNotEmpty()) {
            allBooks = booksMapper.dbListToModelList(books) //books.map { booksMapper.dbEntityToModel(it) }
            emit(DataState.Success(allBooks))
            return@flow
        }

        try {
            val response = booksApi.getAllAvailableBooks()
            val newBooks = response.booksList.map { booksMapper.networkEntityToDBEntity(it) }
            newBooks.forEach { booksDao.insert(it) }
            val newDBBooks = booksDao.getAllBooks()
            allBooks = booksMapper.dbListToModelList(newDBBooks) //newDBBooks.map { booksMapper.dbEntityToModel(it) }
            emit(DataState.Success(allBooks))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}
