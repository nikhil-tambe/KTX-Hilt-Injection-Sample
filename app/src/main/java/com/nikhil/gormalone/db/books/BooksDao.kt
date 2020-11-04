package com.nikhil.gormalone.db.books

import androidx.room.Dao
import androidx.room.Query
import com.nikhil.gormalone.db.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BooksDao : BaseDao<BookEntity> {

    @Query("select * from books order by id")
    abstract suspend fun getAllBooks(): List<BookEntity>

    @Query("select count(*) from books ")
    abstract suspend fun getAllBooksCount(): Int

}
