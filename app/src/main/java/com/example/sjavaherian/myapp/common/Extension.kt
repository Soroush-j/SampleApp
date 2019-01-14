package com.example.sjavaherian.myapp.common

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.sjavaherian.myapp.R

fun shortToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun logd(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun loge(tag: String, msg: String) {
    Log.e(tag, msg)
}

fun loge(tag: String, e: Throwable) {
    Log.e(tag, e.message, e)
}

fun logw(tag: String, msg: String) {
    Log.w(tag, msg)
}

fun snackbar(view: View, msg: String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
}

fun replaceFragment(
    activity: AppCompatActivity,
    fragment: Fragment,
    tag: String,
    addToBackStack: Boolean = true,
    container: Int = R.id.container
) {
    val transaction = activity.supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, tag)
    if (addToBackStack) transaction.addToBackStack(tag)
    transaction.commit()
}