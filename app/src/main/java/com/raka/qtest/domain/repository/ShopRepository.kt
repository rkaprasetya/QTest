package com.raka.qtest.domain.repository

import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.local.ProductPromoLocal
import io.reactivex.Single

interface ShopRepository {

    fun getProductList(): Single<List<ProductPromoLocal>>
    fun getProduct(id:String):Single<ProductPromoLocal>
    fun getCategoryList():Single<List<CategoryLocal>>
    fun insertProductList(list:List<ProductPromoLocal>)
//    fun getCategory():Single<CategoryLocal>
}