package com.nikhil.gormalone.ui.product

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.gormalone.R
import com.nikhil.gormalone.model.product.Product
import com.nikhil.gormalone.util.DataState
import com.nikhil.gormalone.util.error_model.ProductInputError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class AddProductViewModel
@ViewModelInject constructor(
    private val productsRepo: ProductsRepo
) : ViewModel() {

    val productName = MutableLiveData<String>()
    val productDescription = MutableLiveData<String>()
    val productQuantity = MutableLiveData<String>()
    val productPrice = MutableLiveData<String>()

    private val _productInputError = MutableLiveData<ProductInputError>()
    val productInputError: LiveData<ProductInputError>
        get() = _productInputError

    private val _productInserted: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val productInserted: LiveData<DataState<Boolean>>
        get() = _productInserted

    fun validateAndAddProduct() {
        val name = productName.value
        val desc = productDescription.value
        val quantity = productQuantity.value
        val price = productPrice.value

        if (isNameInvalid(name)) return
        if (isDescriptionInvalid(desc)) return
        if (isQuantityInvalid(quantity)) return
        if (isPriceInvalid(price)) return

        addNewProduct(
            Product(
            product_name = name!!,
            product_desc = desc!!,
            product_quantity = quantity!!,
            product_price = price!!,
            isUploaded = false
        )
        )
    }

    private fun isPriceInvalid(price: String?): Boolean {
        if (price.isNullOrBlank()) {
            _productInputError.postValue(ProductInputError.Price(R.string.error_price_blank))
            return true
        }
        try {
            if (price.toInt() > 99999) {
                _productInputError.postValue(ProductInputError.Price(R.string.error_price_5_digits))
                return true
            }
        } catch (e: Exception) {
            _productInputError.postValue(ProductInputError.Price(R.string.error_invalid_number))
            return true
        }
        return false
    }

    private fun isQuantityInvalid(quantity: String?): Boolean {
        if (quantity.isNullOrBlank()) {
            _productInputError.postValue(ProductInputError.Quantity(R.string.error_quantity_blank))
            return true
        }
        try {
            if (quantity.toInt() > 9999) {
                _productInputError.postValue(ProductInputError.Quantity(R.string.error_quantity_4_digits))
                return true
            }
        } catch (e: Exception) {
            _productInputError.postValue(ProductInputError.Quantity(R.string.error_invalid_number))
            return true
        }
        return false
    }

    private fun isDescriptionInvalid(desc: String?): Boolean {
        if (desc.isNullOrBlank()) {
            _productInputError.postValue(ProductInputError.Description(R.string.error_desc_blank))
            return true
        }
        if (desc.trim().length > 100) {
            _productInputError.postValue(ProductInputError.Description(R.string.error_desc_100_chars))
            return true
        }
        return false
    }

    private fun isNameInvalid(name: String?): Boolean {
        if (name.isNullOrBlank()) {
            _productInputError.postValue(ProductInputError.Name(R.string.error_name_blank))
            return true
        }
        if (name.trim().length > 30) {
            _productInputError.postValue(ProductInputError.Name(R.string.error_name_30_chars))
            return true
        }
        return false
    }

    private fun addNewProduct(product: Product) {
        viewModelScope.launch {
            productsRepo.addProduct(product)
                .onEach {
                    if (it is DataState.Success) {
                        clearForm()
                    }
                    _productInserted.postValue(it)
                }
                .launchIn(viewModelScope)
        }
    }

    private fun clearForm() {
        productName.postValue("")
        productDescription.postValue("")
        productPrice.postValue("")
        productQuantity.postValue("")
    }

}
