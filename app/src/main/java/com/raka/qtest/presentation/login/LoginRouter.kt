package com.raka.qtest.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.raka.qtest.presentation.main.HomeActivity
import java.lang.ref.WeakReference

class LoginRouter(private val activityRef: WeakReference<Activity>) {
    enum class Route{
        HOME_SCREEN,
    }

    fun navigate(route:Route){
        when(route){
            Route.HOME_SCREEN -> toNextScreen(HomeActivity::class.java)
        }
    }

    private fun toNextScreen(clazz: Class<*>){
        activityRef.get()?.startActivity(Intent(activityRef.get(),clazz))
    }

}