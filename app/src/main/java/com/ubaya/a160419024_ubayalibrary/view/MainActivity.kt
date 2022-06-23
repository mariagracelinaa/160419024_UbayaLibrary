package com.ubaya.a160419024_ubayalibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ubaya.a160419024_ubayalibrary.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment)
                as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController)

        //Buat nampilin atau sembunyikan bottom nav di fragment tertentu
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) {
                bottomNav.visibility = View.GONE
                navView.visibility = View.GONE
            }
            else {
                NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
                NavigationUI.setupWithNavController(navView, navController)
                bottomNav.setupWithNavController(navController)
                bottomNav.visibility = View.VISIBLE
                navView.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }

}