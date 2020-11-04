package com.nikhil.gormalone.ui.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.nikhil.gormalone.R
import com.nikhil.gormalone.util.DataState
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_books.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.fragment_books) {

    private val booksViewModel: BooksViewModel by viewModels()
    private val adapter = BooksRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        books_RecyclerView.adapter = adapter

        booksViewModel.bookListDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {

                }
                is DataState.Success -> {
                    adapter.submitList(it.data)
                }
                is DataState.Error -> {
                    Timber.e(it.e)
                    it.e.localizedMessage?.let { s ->
                        Snackbar.make(requireView(), s, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        booksViewModel.getAllBooks()
    }

}