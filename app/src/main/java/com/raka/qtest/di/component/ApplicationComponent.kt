package com.raka.qtest.di.component

import com.raka.qtest.QTestApplication
import com.raka.qtest.data.api.ApiService
import com.raka.qtest.data.db.AppDatabase
import com.raka.qtest.di.module.ApplicationModule
import com.raka.qtest.di.module.DatabaseModule
import com.raka.qtest.di.module.NetworkModule
import com.raka.qtest.di.scope.ApplicationScope
import dagger.Component


@ApplicationScope
@Component(modules = [NetworkModule::class, DatabaseModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun getApiService(): ApiService
    fun getAppDatabase(): AppDatabase
    fun inject(application: QTestApplication)
}