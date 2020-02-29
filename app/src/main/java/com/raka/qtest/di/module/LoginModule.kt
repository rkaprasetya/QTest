package com.raka.qtest.di.module

import android.app.Activity
import com.raka.qtest.di.scope.ActivityScope
import com.raka.qtest.presentation.login.LoginRouter
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class LoginModule(private val activity: Activity) {
    @ActivityScope
    @Provides
    fun provideLoginRouter(): LoginRouter = LoginRouter(WeakReference(activity))
}