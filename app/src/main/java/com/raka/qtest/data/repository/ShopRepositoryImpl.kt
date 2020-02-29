package com.raka.qtest.data.repository

import com.raka.qtest.data.api.ShopApi
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.data.mapper.CategoryMapper
import com.raka.qtest.data.mapper.ProductMapper
import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.domain.repository.ShopRepository
import io.reactivex.Single

class ShopRepositoryImpl(
    private val shopApi: ShopApi,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper,
    private val appDatabase: AppDatabase
) : ShopRepository {

    override fun insertProductList(list: List<ProductPromoLocal>) {
        appDatabase.parametersDao().insertNewProducts(list)
    }

    override fun getProductList(): Single<List<ProductPromoLocal>> {
        return shopApi.getShopData().map { productMapper.mapList(it.data!!.productPromo) }
    }

    override fun getProduct(id: String): Single<ProductPromoLocal> {
        return appDatabase.parametersDao().getProductById(id)
    }

//    override fun getCategory(): Single<CategoryLocal> {
//
//    }

    override fun getCategoryList(): Single<List<CategoryLocal>> {
        return shopApi.getShopData().map { categoryMapper.mapList(it.data!!.category) }
    }
}