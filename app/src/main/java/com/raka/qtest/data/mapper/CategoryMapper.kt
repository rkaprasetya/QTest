package com.raka.qtest.data.mapper

import com.raka.qtest.data.model.compact.CategoryCompact
import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.remote.CategoryRemote
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun mapList(responseList: List<CategoryRemote?>?): List<CategoryCompact> {
        return responseList!!.map { (map(it!!)) }
    }

    fun map(response: CategoryRemote): CategoryCompact {
        return CategoryCompact(response.imageUrl,
            response.name,
            response.id)
    }
}