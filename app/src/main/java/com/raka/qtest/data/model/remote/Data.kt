package com.raka.qtest.data.model.remote

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("productPromo")
	val productPromo: List<ProductPromoRemote?>? = null,

	@field:SerializedName("category")
	val category: List<CategoryRemote?>? = null
)