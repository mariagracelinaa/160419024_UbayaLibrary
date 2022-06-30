package com.ubaya.a160419024_ubayalibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.ubaya.a160419024_ubayalibrary.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.itemLogin,
                R.id.registerFragment
            )
        )

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        setupActionBarWithNavController(navController!!, appBarConfiguration!!)

        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.itemBuku -> showNav()
                R.id.itemProfil -> showNav()
                R.id.itemRuang -> showNav()
                else -> hideNav()
            }
        }
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        NavigationUI.setupWithNavController(navView, navController)
//        bottomNav.setupWithNavController(navController)
    }

    private fun showNav() {
        bottomNav.visibility = View.VISIBLE
        navView.visibility = View.VISIBLE
    }

    private fun hideNav() {
        bottomNav.visibility = View.GONE
        navView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp()
    }

}