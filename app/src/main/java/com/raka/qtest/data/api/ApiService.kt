package com.raka.qtest.data.api

import com.raka.qtest.data.model.remote.DataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    fun getShopData():Single<DataResponse>
}