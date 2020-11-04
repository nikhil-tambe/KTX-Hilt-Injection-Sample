package com.nikhil.gormalone.di

import com.nikhil.gormalone.db.books.BooksDao
import com.nikhil.gormalone.db.products.ProductsDao
import com.nikhil.gormalone.model.book.BooksMapper
import com.nikhil.gormalone.model.product.ProductMapper
import com.nikhil.gormalone.network.BooksApi
import com.nikhil.gormalone.ui.books.BooksRepo
import com.nikhil.gormalone.ui.main.MainRepo
import com.nikhil.gormalone.ui.product.ProductsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideBooksRepo(
        booksApi: BooksApi,
        booksDao: BooksDao,
        booksMapper: BooksMapper
    ): BooksRepo {
        return BooksRepo(
            booksApi = booksApi,
            booksDao = booksDao,
            booksMapper = booksMapper
        )
    }

    @Singleton
    @Provides
    fun provideProductsRepo(
        productsDao: ProductsDao,
        productMapper: ProductMapper
    ): ProductsRepo {
        return ProductsRepo(
            productsDao = productsDao,
            productMapper = productMapper
        )
    }

    @Singleton
    @Provides
    fun provideMainRepo(
        booksDao: BooksDao,
        productsDao: ProductsDao
    ): MainRepo {
        return MainRepo(
            booksDao = booksDao,
            productsDao = productsDao
        )
    }

}