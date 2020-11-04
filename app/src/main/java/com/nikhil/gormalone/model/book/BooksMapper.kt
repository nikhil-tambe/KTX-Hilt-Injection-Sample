package com.nikhil.gormalone.model.book

import com.nikhil.gormalone.db.books.BookEntity
import com.nikhil.gormalone.network.response.ResponseBooks
import com.nikhil.gormalone.util.Mapper
import javax.inject.Inject

class BooksMapper
@Inject constructor() : Mapper<ResponseBooks, BookEntity, Book> {

    // For storing books from network to local db
    override fun networkEntityToDBEntity(networkEntity: ResponseBooks): BookEntity {
        return BookEntity(
            book_id = networkEntity.book_id,
            book_name = networkEntity.book_name,
            book_desc = networkEntity.book_desc,
            book_author = networkEntity.book_author,
            book_price = networkEntity.book_price,
            book_img_url = networkEntity.book_img_url
        )
    }

    // For UI
    override fun dbEntityToModel(dbEntity: BookEntity): Book {
        return Book(
            book_id = dbEntity.book_id,
            book_name = dbEntity.book_name,
            book_desc = dbEntity.book_desc,
            book_author = dbEntity.book_author,
            book_price = dbEntity.book_price,
            book_img_url = dbEntity.book_img_url
        )
    }

    // NA for this module as no books are being uploaded
    override fun dbEntityToNetworkEntity(dbEntity: BookEntity): ResponseBooks {
        TODO("Not yet implemented")
    }

    fun dbListToModelList(books: List<BookEntity>): List<Book> {
        return books.map { dbEntityToModel(it) }
    }

}
