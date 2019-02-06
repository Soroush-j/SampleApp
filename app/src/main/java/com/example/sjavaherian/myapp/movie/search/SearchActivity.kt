package com.example.sjavaherian.myapp.movie.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.example.sjavaherian.myapp.R

class SearchActivity : AppCompatActivity() {
    private val TAG = "tag SearchActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
}
