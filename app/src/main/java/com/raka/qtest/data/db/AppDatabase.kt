package com.raka.qtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raka.qtest.data.model.local.CategoryLocal
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.utilities.AppConfig
import dagger.Module

@Database(
    entities = [CategoryLocal::class, ProductPromoLocal::class],
    version = AppConfig.DB_VERSION
)
@Module
abstract class AppDatabase : RoomDatabase() {
    abstract fun parametersDao(): ParametersDao
}