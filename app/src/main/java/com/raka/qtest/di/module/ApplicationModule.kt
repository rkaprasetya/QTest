package com.raka.qtest.di.module

import android.content.Context
import com.raka.qtest.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @ApplicationScope
    fun getContext() = context
}