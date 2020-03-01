package com.raka.qtest.data.api

import com.raka.qtest.data.model.remote.Data
import com.raka.qtest.data.model.remote.DataResponse
import com.raka.qtest.data.model.remote.ProductPromoRemote
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    fun getShopData():Single<List<DataResponse>>
}