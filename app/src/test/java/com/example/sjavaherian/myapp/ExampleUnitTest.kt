package com.example.sjavaherian.myapp

import android.databinding.ObservableField
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val text = ObservableField<String>()
    @Test
    fun addition_isCorrect() {
        assertEquals(null, text.get())
    }

    @Test
    fun checkTaskHasChanged() {
        print(("one" != "two") || ("one" != "one"))
    }
}
