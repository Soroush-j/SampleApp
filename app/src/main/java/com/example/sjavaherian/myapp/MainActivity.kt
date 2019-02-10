package com.example.sjavaherian.myapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Tag MainActivity"

    lateinit var navController: NavController
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupWithNavController(main_bottom_nav, navController)

        bottomNavigation = findViewById(R.id.main_bottom_nav)

//        bottomNavigation.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_task_graph -> {
//                    navController.navigate(R.id.action_global_task_nav_graph)
//                    true
//                }
////                R.id.nav_movies_graph->{navController.navigate()}
//                else -> {
//                    false
//                }
//            }
//        }
    }
}
