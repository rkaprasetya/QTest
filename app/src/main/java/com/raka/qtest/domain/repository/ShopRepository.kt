package com.raka.qtest.domain.repository

import com.raka.qtest.data.model.compact.CategoryCompact
import com.raka.qtest.data.model.compact.ProductCompact
import com.raka.qtest.data.model.local.ProductPromoLocal
import io.reactivex.Single

interface ShopRepository {
    fun getProductList(): Single<List<ProductCompact>>
    fun getProduct(id:String):Single<ProductPromoLocal>
    fun getCategoryList():Single<List<CategoryCompact>>
    fun insertProductList(list:List<ProductPromoLocal>)
    fun updateLikedProduct(liked:Int,id:String)
    fun getProductListFromDb():Single<List<ProductCompact>>
}