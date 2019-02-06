package com.example.sjavaherian.myapp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.sjavaherian.myapp.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Tag MainActivity"

    companion object {
        const val SHARED_PREF = "SHARED_PREF"
        const val SHARED_GENRE_ID = "SHARED_GENRE_ID"
    }

    lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        mNavController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupWithNavController(main_bottom_nav, mNavController)
    }
}
