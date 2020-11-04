package com.nikhil.gormalone.model.product

import com.nikhil.gormalone.db.products.ProductEntity
import com.nikhil.gormalone.network.request.ProductBody
import com.nikhil.gormalone.util.Mapper
import javax.inject.Inject

class ProductMapper
@Inject constructor() : Mapper<ProductBody, ProductEntity, Product> {

    // NA in this case as products are not being downloaded
    override fun networkEntityToDBEntity(networkEntity: ProductBody): ProductEntity {
        TODO("Not yet implemented")
    }

    // For local DB inserts
    fun modelToDBEntity(product: Product): ProductEntity {
        return ProductEntity(
            product_name = product.product_name,
            product_desc = product.product_desc,
            product_price = product.product_price.toInt(),
            product_quantity = product.product_quantity.toInt(),
            isUploaded = false
        )
    }

    // For upload
    override fun dbEntityToNetworkEntity(dbEntity: ProductEntity): ProductBody {
        return ProductBody(
            product_name = dbEntity.product_name,
            product_desc = dbEntity.product_desc,
            product_price = dbEntity.product_price,
            product_quantity = dbEntity.product_quantity
        )
    }

    // For UI
    override fun dbEntityToModel(dbEntity: ProductEntity): Product {
        return Product(
            product_id = dbEntity.product_id,
            product_name = dbEntity.product_name,
            product_desc = dbEntity.product_desc,
            product_price = dbEntity.product_price.toString(),
            product_quantity = dbEntity.product_quantity.toString(),
            isUploaded = dbEntity.isUploaded
        )
    }

    fun dbListToModelList(products: List<ProductEntity>): List<Product> {
        return products.map { dbEntityToModel(it) }
    }

}
