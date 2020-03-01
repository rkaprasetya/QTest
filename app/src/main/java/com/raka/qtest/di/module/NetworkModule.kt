package com.raka.qtest.di.module

import com.raka.qtest.data.api.ApiService
import com.raka.qtest.di.scope.ApplicationScope
import com.raka.qtest.di.scope.ScreenScope
import com.raka.qtest.utilities.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun getOkhttpClient() = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
    @Provides
    @ApplicationScope
    fun getRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(AppConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @ApplicationScope
    fun getApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}