package com.adriesavana.network.rest.movie

import com.adriesavana.data.movie.entity.MovieListEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieApi {

    @GET
    fun getListMovie(@Url() url: String): Single<MovieListEntity>

}