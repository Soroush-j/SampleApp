package com.example.sjavaherian.myapp.main

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.example.sjavaherian.myapp.R
import kotlinx.android.synthetic.main.search_activity.*

class SearchActivity : AppCompatActivity() {

    private val TAG = "tag SearchActivity"

    lateinit var query: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.also {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            title = "Search"
        }

        Log.d(TAG, "SearchActivity started.")

        if (intent.action == Intent.ACTION_SEARCH) {
            query = intent.getStringExtra(SearchManager.QUERY)
        }
        tv_search.text = query
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SearchActivity stopped.")
    }
}
