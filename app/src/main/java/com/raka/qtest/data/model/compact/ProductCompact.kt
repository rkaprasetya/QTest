package com.raka.qtest.data.model.compact

data class ProductCompact(
    val id: String? = null,
    val imageUrl: String? = null,
    var liked : Int = 0,
    val productName : String = ""
) {
}