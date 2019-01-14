package com.example.sjavaherian.myapp.movie.movies.network.pojo

import com.google.gson.annotations.SerializedName

data class MetadataRetro(

    @field:SerializedName("per_page")
    var moviesPerPage: Int? = null,

    @field:SerializedName("total_count")
    var moviesTotalCount: Int? = null,

    @field:SerializedName("current_page")
    var currentPage: Int,

    @field:SerializedName("page_count")
    var pageCount: Int
)