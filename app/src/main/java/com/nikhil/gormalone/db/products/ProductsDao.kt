package com.nikhil.gormalone.db.products

import androidx.room.Dao
import androidx.room.Query
import com.nikhil.gormalone.db.BaseDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@ExperimentalCoroutinesApi
@Dao
abstract class ProductsDao : BaseDao<ProductEntity> {

    @Query("select * from products order by id")
    abstract suspend fun getAllProducts(): List<ProductEntity>

    @Query("select count(*) from products")
    abstract fun getAllProductsCount(): Flow<Int>

    @Query("select count(*) from products where isUploaded=0")
    abstract suspend fun getProductsForUploadCount(): Int

    //fun getDistinctProductForUploadCount() = getProductsForUploadCount().distinctUntilChanged()

    @Query("select * from products where isUploaded=0")
    abstract suspend fun getProductsForUpload(): List<ProductEntity>


}
