package com.codeliner.moviestutu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.codeliner.moviestutu.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private var moviesBinding: ActivityMainBinding? = null
    private val binding get() = moviesBinding!!
    private lateinit var navController: NavController

    private var currentMenuItemId = R.id.menu_find_repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host)
        setBottomNavListener()
    }

    private fun setBottomNavListener() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_find_repository -> { currentMenuItemId = R.id.menu_find_repository }
                R.id.menu_about_app -> { currentMenuItemId = R.id.menu_about_app }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesBinding = null

    }

    companion object {
        var REQUEST = "retrofit+kotlin"
    }
}