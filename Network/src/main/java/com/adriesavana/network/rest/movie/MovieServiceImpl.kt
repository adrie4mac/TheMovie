package com.adriesavana.network.rest.movie

import com.adriesavana.data.movie.entity.MovieListEntity
import com.adriesavana.data.movie.repository.MovieService
import io.reactivex.Single
import retrofit2.Retrofit

class MovieServiceImpl(private val retrofit: Retrofit, private val apikey: String) : MovieService {

    private val movieApi by lazy { retrofit.create(MovieApi::class.java) }

    override fun getMovieList(category: String, page: String): Single<MovieListEntity> {
        return movieApi.getListMovie("https://api.themoviedb.org/3/movie/$category?api_key=$apikey&language=en-US&page=$page")
    }
}