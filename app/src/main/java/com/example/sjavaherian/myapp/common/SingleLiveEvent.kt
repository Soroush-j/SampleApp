package com.example.sjavaherian.myapp.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean()
    private val TAG = "SingleLiveEvent"

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        if (hasActiveObservers()) Log.w(
            TAG,
            "Multiple observers registered but only one will be notified of changes."
        )
        super.observe(owner, Observer<T> { event -> if (pending.compareAndSet(true, false)) observer.onChanged(event) })
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }
}