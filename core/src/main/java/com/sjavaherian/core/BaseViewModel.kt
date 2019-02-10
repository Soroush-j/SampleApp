package com.sjavaherian.core

import io.reactivex.disposables.CompositeDisposable

interface BaseViewModel {
    val TAG: String
    fun onStop()

    val mDisposable: CompositeDisposable
}