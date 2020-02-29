package com.raka.qtest.data.model.remote

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @field:SerializedName("data")
    val data: Data? = null
)