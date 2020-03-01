package com.raka.qtest.di.module

import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.raka.qtest.data.api.ApiService
import com.raka.qtest.data.api.ShopApi
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.data.mapper.CategoryMapper
import com.raka.qtest.data.mapper.ProductMapper
import com.raka.qtest.data.repository.ShopRepositoryImpl
import com.raka.qtest.di.scope.ScreenScope
import com.raka.qtest.domain.repository.ShopRepository
import com.raka.qtest.presentation.main.fragment.home.HomeRouter
import com.raka.qtest.utilities.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

@Module
class HomeFragmentModule(private val activity: Activity) {
    @ScreenScope
    @Provides
    fun provideHomeRouter(): HomeRouter = HomeRouter(WeakReference(activity))

    @ScreenScope
    @Provides
    fun getAppdatabase() = Room.databaseBuilder(
        activity,
        AppDatabase::class.java, AppConfig.DB_NAME)
        .build()

    @ScreenScope
    @Provides
    fun provideShopRepository(
        shopApi: ShopApi,
        productMapper: ProductMapper
        , categoryMapper: CategoryMapper
        , appDatabase: AppDatabase
    ): ShopRepository = ShopRepositoryImpl(shopApi, productMapper, categoryMapper, appDatabase)

    @Provides
    @ScreenScope
    fun getOkhttpClient() = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
    @Provides
    @ScreenScope
    fun getRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(AppConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @ScreenScope
    fun getApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}
