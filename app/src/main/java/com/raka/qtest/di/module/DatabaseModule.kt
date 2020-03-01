package com.raka.qtest.di.module

import android.content.Context
import androidx.room.Room
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.di.scope.ApplicationScope
import com.raka.qtest.di.scope.ScreenScope
import com.raka.qtest.utilities.AppConfig
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @ApplicationScope
    @Provides
    fun getAppdatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, AppConfig.DB_NAME)
        .build()
}