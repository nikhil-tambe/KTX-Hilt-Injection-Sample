package com.nikhil.gormalone.ui.main

import com.nikhil.gormalone.db.books.BooksDao
import com.nikhil.gormalone.db.products.ProductsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepo
constructor(
    private val booksDao: BooksDao,
    private val productsDao: ProductsDao
) {

    suspend fun getBooksCount(): Flow<Int> = flow {
        emit(booksDao.getAllBooksCount())
    }

    suspend fun getProductsCount(): Flow<Int> = flow {
        emit(productsDao.getProductsForUploadCount())
    }

}
