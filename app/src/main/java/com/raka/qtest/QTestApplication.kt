package com.raka.qtest

import android.app.Application
import com.raka.qtest.di.component.ApplicationComponent
import com.raka.qtest.di.module.ApplicationModule

class QTestApplication : Application() {
    lateinit var component: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
//        component = DaggerApplicationComponent.builder()
//            .applicationModule(ApplicationModule(this))
//            .build()
//        component.inject(this)
    }

    fun getApplicationComponent() = component
}