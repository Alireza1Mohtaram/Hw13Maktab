package com.alireza.hw13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var navigition: NavController
    lateinit var buttomNav: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navigition = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navigition.graph)


        buttomNav = findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(buttomNav, navigition)


        buttomNav.setOnItemSelectedListener() {
            when (it.itemId) {
                R.id.miHome -> {
                    navigition.navigate(R.id.homeFragment)
                }
                R.id.miUpload -> {
                    navigition.navigate(R.id.uploadFragment)
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}