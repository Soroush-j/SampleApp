package com.example.sjavaherian.myapp.common

import io.reactivex.disposables.CompositeDisposable

interface BaseViewModel {
    val TAG: String
    fun onStop()

    val mDisposable: CompositeDisposable
}