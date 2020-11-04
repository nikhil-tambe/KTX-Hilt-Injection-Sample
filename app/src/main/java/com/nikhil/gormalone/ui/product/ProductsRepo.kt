package com.nikhil.gormalone.ui.product

import com.nikhil.gormalone.db.products.ProductsDao
import com.nikhil.gormalone.model.product.Product
import com.nikhil.gormalone.model.product.ProductMapper
import com.nikhil.gormalone.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class ProductsRepo
constructor(
    private val productsDao: ProductsDao,
    private val productMapper: ProductMapper
) {

    suspend fun addProduct(product: Product): Flow<DataState<Boolean>> = flow {
        try {
            productsDao.insert(productMapper.modelToDBEntity(product))
            emit(DataState.Success(true))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}
