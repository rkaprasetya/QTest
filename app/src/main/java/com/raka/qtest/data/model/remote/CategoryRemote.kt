package com.raka.qtest.data.model.remote

import com.google.gson.annotations.SerializedName

data class CategoryRemote(
    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("id")
    val id: Int? = null
)