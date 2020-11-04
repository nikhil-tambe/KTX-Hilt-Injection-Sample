package com.nikhil.gormalone.util.error_model

sealed class ProductInputError {

    data class Name(val message: Int) : ProductInputError()
    data class Description(val message: Int) : ProductInputError()
    data class Quantity(val message: Int) : ProductInputError()
    data class Price(val message: Int) : ProductInputError()

}