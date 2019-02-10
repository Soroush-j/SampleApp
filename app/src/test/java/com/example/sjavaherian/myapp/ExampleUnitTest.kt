package com.example.sjavaherian.myapp

import com.sjavaherian.core.database.movies.Genre
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test() {
        val db = listOf(
            Genre(key = null, id = 1, name = "Crime"),
            Genre(key = null, id = 2, name = "Drama"),
            Genre(key = null, id = 3, name = "Action"),
            Genre(key = null, id = 4, name = "Biography"),
            Genre(key = null, id = 5, name = "History"),
            Genre(key = null, id = 6, name = "Adventure"),
            Genre(key = null, id = 7, name = " Fantasy"),
            Genre(key = null, id = 8, name = " Western"),
            Genre(key = null, id = 9, name = " Comedy"),
            Genre(key = null, id = 10, name = "Sci - Fi"),
            Genre(key = null, id = 11, name = "Mystery"),
            Genre(key = null, id = 12, name = "Thriller"),
            Genre(key = null, id = 13, name = "Family"),
            Genre(key = null, id = 14, name = "War"),
            Genre(key = null, id = 15, name = "Animation"),
            Genre(key = null, id = 16, name = "Romance"),
            Genre(key = null, id = 17, name = "Horror"),
            Genre(key = null, id = 18, name = "Music"),
            Genre(key = null, id = 19, name = "Film - Noir"),
            Genre(key = null, id = 20, name = "Musical"),
            Genre(key = null, id = 21, name = "Sport")
        )
        var fin: List<Genre>
        fin = db.filter { shouldBeFiltered(it) }
        System.out.println(fin.size)
        System.out.println(fin)
    }

    private fun shouldBeFiltered(g: Genre): Boolean {
        val internet = listOf(
            Genre(key = null, id = 1, name = "Crime"),
            Genre(key = null, id = 2, name = "Drama"),
            Genre(key = null, id = 3, name = "Action"),
            Genre(key = null, id = 4, name = "Biography"),
            Genre(key = null, id = 5, name = "History"),
            Genre(key = null, id = 6, name = "Adventure"),
            Genre(key = null, id = 7, name = " Fantasy"),
            Genre(key = null, id = 8, name = " Western")
        )
        for (i in internet) {
            if(i.equals(g)) return false
        }
        return true
    }

}
