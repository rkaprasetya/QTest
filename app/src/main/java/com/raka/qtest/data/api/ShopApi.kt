package com.raka.qtest.data.api

import com.raka.qtest.data.model.remote.Data
import com.raka.qtest.data.model.remote.DataResponse
import com.raka.qtest.data.model.remote.ProductPromoRemote
import io.reactivex.Single
import javax.inject.Inject

class ShopApi @Inject constructor(val apiService: ApiService) {

    fun getShopData():Single<List<DataResponse>> = apiService.getShopData()
}