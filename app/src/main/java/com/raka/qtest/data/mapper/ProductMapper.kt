package com.raka.qtest.data.mapper

import com.raka.qtest.data.model.compact.ProductCompact
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.data.model.remote.ProductPromoRemote
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapList(responseList: List<ProductPromoRemote?>?): List<ProductCompact> {
        return responseList!!.map { (map(it!!)) }
    }

    fun map(response: ProductPromoRemote): ProductCompact {
        return ProductCompact(
            response.id,
            response.imageUrl,
            response.loved!!,
            response.title!!
        )
    }

    fun mapListToLocal(responseList: List<ProductPromoRemote?>?): List<ProductPromoLocal> {
        return responseList!!.map { (mapRemoteToLocal(it!!)) }
    }

    fun mapRemoteToLocal(response: ProductPromoRemote): ProductPromoLocal {
        return ProductPromoLocal(0,
            response.loved,
            response.price,
            response.imageUrl,
            response.description,
            response.id,
            response.title
        )
    }

    fun mapLocalListToCompact(list: List<ProductPromoLocal?>?):List<ProductCompact>{
        return list!!.map { mapLocalToCompact(it!!) }
    }

    fun mapLocalToCompact(response: ProductPromoLocal): ProductCompact {
        return ProductCompact(
            response.id,
            response.imageUrl,
            response.loved!!,
            response.title!!
        )
    }
}