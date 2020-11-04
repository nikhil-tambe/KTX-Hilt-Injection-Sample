package com.nikhil.gormalone.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.gormalone.R
import com.nikhil.gormalone.databinding.LayoutItemBookBinding
import com.nikhil.gormalone.model.book.Book

class BooksRecyclerAdapter :
    ListAdapter<Book, BooksRecyclerAdapter.BookViewHolder>(BookDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder private constructor(private val binding: LayoutItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): BookViewHolder {
                val inflate = DataBindingUtil.inflate<LayoutItemBookBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_book,
                    parent,
                    false
                )
                return BookViewHolder(inflate)
            }
        }

        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()
        }

    }
}

class BookDiffUtil : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.book_id == newItem.book_id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}
