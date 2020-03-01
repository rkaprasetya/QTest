package com.raka.qtest.presentation.main.fragment.home

import android.app.Activity
import android.os.Bundle
import java.lang.ref.WeakReference

class HomeRouter(private val activityRef: WeakReference<Activity>) {
    enum class Route{
        DETAIL_PRODUCT,
    }

    fun navigate(route:Route,bundle: Bundle = Bundle()){
        when(route){
          //  Route.DETAIL_PRODUCT -> toNextScreen(HomeActivity::class.java)
        }
    }

    private fun toNextScreen(clazz: Class<*>, bundle: Bundle?){
       // activityRef.get()?.startActivity(Intent(activityRef.get(),clazz))
    }
}