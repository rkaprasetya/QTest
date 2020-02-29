package com.raka.qtest.data.mapper

import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.remote.CategoryRemote

class CategoryMapper {
    fun mapList(responseList: List<CategoryRemote?>?): List<CategoryLocal> {
        return responseList!!.map { (map(it!!)) }
    }

    fun map(response: CategoryRemote): CategoryLocal {
        return CategoryLocal(
            0,
            response.imageUrl,
            response.name,
            response.id
        )
    }
}