package com.raka.qtest.data.mapper

import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.data.model.remote.ProductPromoRemote

class ProductMapper {

    fun mapList(responseList:List<ProductPromoRemote?>?):List<ProductPromoLocal>{
        return responseList!!.map { (map(it!!)) }
    }

    fun map(response:ProductPromoRemote):ProductPromoLocal{
        return ProductPromoLocal(0,
            response.loved,
            response.price,
            response.imageUrl,
            response.description,
            response.id,
            response.title)
    }
}