package com.sjavaherian.movie.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sjavaherian.core.database.movies.Genre
import com.sjavaherian.movie.R

class SpinnerAdapter(activity: Context) : ArrayAdapter<Genre>(activity, 0) {

    private var mGenres = mutableListOf<Genre>(Genre(-1, "Loading"))
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(activity)

    constructor(context: Context, objects: List<Genre>) : this(context) {
        mGenres.clear()
        mGenres.addAll(objects)
        notifyDataSetChanged()
    }

    fun addAll(genres: List<Genre>) {
        mGenres.clear()
        mGenres.add(0, Genre(0, "All"))
        mGenres.addAll(genres)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Genre = mGenres[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        createItemView(convertView, parent, position)


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        createItemView(convertView, parent, position)

    private fun createItemView(convertView: View?, parent: ViewGroup, position: Int): View {
        val itemView = convertView ?: mLayoutInflater.inflate(R.layout.genre_item, parent, false)

        val name = itemView.findViewById<TextView>(R.id.genre_item_name)

        name.text = mGenres[position].name
        return itemView
    }

    override fun getCount(): Int = mGenres.size
}