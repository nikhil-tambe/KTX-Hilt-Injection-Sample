package com.nikhil.gormalone.di

import android.content.Context
import androidx.room.Room
import com.nikhil.gormalone.db.GormalOneDB
import com.nikhil.gormalone.db.books.BooksDao
import com.nikhil.gormalone.db.products.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): GormalOneDB {
        return Room.databaseBuilder(context, GormalOneDB::class.java, GormalOneDB.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBooksDao(gormalOneDB: GormalOneDB): BooksDao {
        return gormalOneDB.booksDao
    }

    @Singleton
    @Provides
    fun provideProductsDao(gormalOneDB: GormalOneDB): ProductsDao {
        return gormalOneDB.productsDao
    }

}