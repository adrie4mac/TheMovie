package com.adriesavana.data.movie.repository

import com.adriesavana.data.movie.entity.MovieListEntity
import io.reactivex.Single

interface MovieService {
    fun getMovieList(category: String, page: String): Single<MovieListEntity>
}