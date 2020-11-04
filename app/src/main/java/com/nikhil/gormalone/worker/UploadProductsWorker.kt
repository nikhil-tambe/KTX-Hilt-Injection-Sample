package com.nikhil.gormalone.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nikhil.gormalone.db.products.ProductsDao
import com.nikhil.gormalone.network.ProductsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

@ExperimentalCoroutinesApi
class UploadProductsWorker
@WorkerInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val productsDao: ProductsDao,
    private val productsApi: ProductsApi
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            val listOfProducts = productsDao.getProductsForUpload()
            listOfProducts.forEach {
                val map = mapOf<String, String>(
                    "product_name" to it.product_name,
                    "product_desc" to it.product_desc,
                    "product_quantity" to it.product_quantity.toString(),
                    "product_price" to it.product_price.toString(),
                    "user_mobile_no" to "9730041804"
                )
                val response = productsApi.addNewProduct(map)
                Timber.d(response)

                it.isUploaded = true
                productsDao.update(it)
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure()
        }
        Result.success()
    }


}