package com.nikhil.gormalone.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.nikhil.gormalone.R
import com.nikhil.gormalone.databinding.FragmentAddProductBinding
import com.nikhil.gormalone.util.DataState
import com.nikhil.gormalone.util.error_model.ProductInputError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AddProductFragment : Fragment(R.layout.fragment_add_product) {

    private val viewModel: AddProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAddProductBinding.bind(view)
        binding.viewModel = viewModel

        subscribeToListeners()

        saveProduct_Button.setOnClickListener {
            viewModel.validateAndAddProduct()
        }
    }

    private fun subscribeToListeners() {
        viewModel.productInserted.observe(viewLifecycleOwner) {
            when(it) {
                is DataState.Success -> {
                    val message = if (it.data) "Success" else "Failed"
                    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                }
                is DataState.Error -> {
                    val message = it.e.localizedMessage
                    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.productInputError.observe(viewLifecycleOwner) {
            when(it){
                is ProductInputError.Name -> {
                    name_TIL.error = getString(it.message)
                }
                is ProductInputError.Description -> {
                    desc_TIL.error = getString(it.message)
                }
                is ProductInputError.Price -> {
                    price_TIL.error = getString(it.message)
                }
                is ProductInputError.Quantity -> {
                    quantity_TIL.error = getString(it.message)
                }
            }
        }
    }

}