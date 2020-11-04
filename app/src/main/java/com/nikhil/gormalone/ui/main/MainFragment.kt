package com.nikhil.gormalone.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.nikhil.gormalone.R
import com.nikhil.gormalone.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        books_Button.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_mainFragment_to_booksFragment)
        }

        addProducts_Button.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_mainFragment_to_addProductFragment)
        }

        uploadProducts_Button.setOnClickListener {
            viewModel.uploadNewProducts(requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllStatus()
    }

}