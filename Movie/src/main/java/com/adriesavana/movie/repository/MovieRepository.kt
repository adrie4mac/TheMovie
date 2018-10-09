package com.adriesavana.movie.repository

import com.adriesavana.movie.model.MovieList
import io.reactivex.Single

interface MovieRepository {

    fun getMovieList(category: String, page: String): Single<MovieList>
}