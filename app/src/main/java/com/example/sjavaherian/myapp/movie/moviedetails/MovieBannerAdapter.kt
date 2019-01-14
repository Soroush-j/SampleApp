package com.example.sjavaherian.myapp.movie.moviedetails

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.common.loge
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MovieBannerAdapter(private val context: Context) : PagerAdapter() {
    val TAG = "tag MovieBannerAdapter"

    private var images = mutableListOf<String>()

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)

        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        picasso.isLoggingEnabled = true

        picasso.load(images[position])
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_movie)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    Log.d(TAG, "picasso loaded the image!")
                }

                override fun onError(e: Exception?) {
                    loge(TAG, "picasso failed at loading image!" + e?.stackTrace)
                }
            })

        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun addImagesUrl(urls: List<String>) {
        images.clear()
        images.addAll(urls)
        notifyDataSetChanged()
    }
}