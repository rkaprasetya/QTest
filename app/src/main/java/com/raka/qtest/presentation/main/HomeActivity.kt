package com.raka.qtest.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raka.qtest.QTestApplication
import com.raka.qtest.R
import com.raka.qtest.di.component.DaggerHomeComponent
import com.raka.qtest.di.component.HomeComponent
import com.raka.qtest.di.component.LoginComponent
import com.raka.qtest.presentation.main.fragment.home.HomeFragment
import com.raka.qtest.presentation.main.fragment.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var component: HomeComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUi()
        setupDagger()
        loadFragment(HomeFragment())
    }

    private fun setupUi() {
        nav_view.setOnNavigationItemSelectedListener(this)
    }

    private fun setupDagger() {
        component = DaggerHomeComponent.builder().applicationComponent((application as QTestApplication)
            .getApplicationComponent())
            .build()
        component.inject(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_profile -> NotificationsFragment()
            else -> return false
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        return true
    }
}
