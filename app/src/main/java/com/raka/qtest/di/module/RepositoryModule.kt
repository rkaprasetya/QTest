package com.raka.qtest.di.module

import com.raka.qtest.data.api.ShopApi
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.data.mapper.CategoryMapper
import com.raka.qtest.data.mapper.ProductMapper
import com.raka.qtest.data.repository.ShopRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesShopRepository(shopApi: ShopApi,
                               productMapper: ProductMapper,
                               categoryMapper: CategoryMapper,
                               appDatabase: AppDatabase
    ) =
        ShopRepositoryImpl(shopApi,productMapper,categoryMapper, appDatabase)
}