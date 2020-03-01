package com.raka.qtest.di.component

import com.raka.qtest.di.module.HomeFragmentModule
import com.raka.qtest.di.scope.ScreenScope
import com.raka.qtest.presentation.main.fragment.home.HomeFragment
import dagger.Component

@ScreenScope
@Component(modules = [HomeFragmentModule::class],dependencies = [])
interface HomeFragmentComponent {
    fun inject(fragment: HomeFragment)
}