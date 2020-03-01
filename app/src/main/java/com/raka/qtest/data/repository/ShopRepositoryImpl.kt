package com.raka.qtest.data.repository

import android.annotation.SuppressLint
import com.raka.qtest.data.api.ShopApi
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.data.mapper.CategoryMapper
import com.raka.qtest.data.mapper.ProductMapper
import com.raka.qtest.data.model.compact.CategoryCompact
import com.raka.qtest.data.model.compact.ProductCompact
import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.domain.repository.ShopRepository
import io.reactivex.Single
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ShopRepositoryImpl(
    private val shopApi: ShopApi,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper,
    private val appDatabase: AppDatabase
) : ShopRepository, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private var job = Job()
    override fun updateLikedProduct(liked: Int, id: String) {
        launch {
            withContext(Dispatchers.IO) {
                appDatabase.parametersDao().updateLikedProduct(liked, id)
            }
        }
    }

    override fun insertProductList(list: List<ProductPromoLocal>) {
        appDatabase.parametersDao().insertNewProducts(list)
    }

    @SuppressLint("CheckResult")
    override fun getProductList(): Single<List<ProductCompact>> {
        val result = shopApi.getShopData()
        result.map { list ->
            saveNewProductsToDatabase(productMapper.mapListToLocal(list[0].data!!.productPromo))
        }
        return result.map {
            productMapper.mapList(it[0].data!!.productPromo)
        }
    }

    override fun getProductListFromDb(): Single<List<ProductCompact>> {
        return appDatabase.parametersDao().getProductList().map {
            productMapper.mapLocalListToCompact(it)
        }
    }

    private fun saveNewProductsToDatabase(list: List<ProductPromoLocal>) {
        appDatabase.parametersDao().insertNewProducts(list)
    }

    override fun getProduct(id: String): Single<ProductPromoLocal> {
        return appDatabase.parametersDao().getProductById(id)
    }

    override fun getCategoryList(): Single<List<CategoryCompact>> {
        return shopApi.getShopData().map { categoryMapper.mapList(it[0].data!!.category) }
    }
}