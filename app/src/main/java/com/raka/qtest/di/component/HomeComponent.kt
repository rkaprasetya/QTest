package com.raka.qtest.di.component

import com.raka.qtest.di.module.DatabaseModule
import com.raka.qtest.di.module.NetworkModule
import com.raka.qtest.di.scope.ActivityScope
import com.raka.qtest.presentation.main.HomeActivity
import dagger.Component

@ActivityScope
@Component(modules = [DatabaseModule::class,NetworkModule::class],dependencies = [ApplicationComponent::class])
interface HomeComponent {
    fun inject(activity: HomeActivity)
}