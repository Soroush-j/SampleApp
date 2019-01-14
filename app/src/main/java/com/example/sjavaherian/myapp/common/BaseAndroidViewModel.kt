package com.example.sjavaherian.myapp.common

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseAndroidViewModel(app: Application) : AndroidViewModel(app) {

    val disposable = CompositeDisposable()
    fun clearDisposable() {
        disposable.clear()
    }
}
