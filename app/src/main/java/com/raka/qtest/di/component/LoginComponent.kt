package com.raka.qtest.di.component

import com.raka.qtest.di.module.LoginModule
import com.raka.qtest.di.scope.ActivityScope
import com.raka.qtest.presentation.login.LoginActivity
import dagger.Component

@ActivityScope
@Component(modules = [LoginModule::class],dependencies = [ApplicationComponent::class])
interface LoginComponent {
    fun inject(activity: LoginActivity)
}