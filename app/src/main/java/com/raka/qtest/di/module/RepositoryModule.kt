package com.raka.qtest.di.module

import com.raka.qtest.data.api.ShopApi
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.data.mapper.CategoryMapper
import com.raka.qtest.data.mapper.ProductMapper
import com.raka.qtest.data.repository.ShopRepositoryImpl
import com.raka.qtest.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesShopRepository(
        shopApi: ShopApi,
        productMapper: ProductMapper,
        categoryMapper: CategoryMapper,
        appDatabase: AppDatabase
    ): ShopRepository =
            ShopRepositoryImpl(shopApi, productMapper, categoryMapper, appDatabase)
}