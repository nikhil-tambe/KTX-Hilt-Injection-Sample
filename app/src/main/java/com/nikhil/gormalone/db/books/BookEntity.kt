package com.nikhil.gormalone.db.books

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val book_id: Long,

    @ColumnInfo(name = "name")
    val book_name: String,

    @ColumnInfo(name = "desc")
    val book_desc: String,

    @ColumnInfo(name = "author")
    val book_author: String,

    @ColumnInfo(name = "price")
    val book_price: String,

    @ColumnInfo(name = "url")
    val book_img_url: String
)