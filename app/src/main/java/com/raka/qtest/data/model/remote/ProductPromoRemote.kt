package com.raka.qtest.data.model.remote

import com.google.gson.annotations.SerializedName

data class ProductPromoRemote(
    @field:SerializedName("loved")
    val loved: Int? = null,
    @field:SerializedName("price")
    val price: String? = null,
    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("title")
    val title: String? = null
)