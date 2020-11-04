package com.nikhil.gormalone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.gormalone.db.GormalOneDB.Companion.DB_VERSION
import com.nikhil.gormalone.db.books.BookEntity
import com.nikhil.gormalone.db.books.BooksDao
import com.nikhil.gormalone.db.products.ProductEntity
import com.nikhil.gormalone.db.products.ProductsDao
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Database(
    version = DB_VERSION,
    entities = [BookEntity::class, ProductEntity::class]
)
abstract class GormalOneDB : RoomDatabase(){

    companion object {
        const val DB_NAME = "gormal_db"
        const val DB_VERSION = 2
    }

    abstract val booksDao: BooksDao

    abstract val productsDao: ProductsDao

}
