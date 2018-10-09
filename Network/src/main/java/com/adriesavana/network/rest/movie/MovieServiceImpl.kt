package com.adriesavana.network.rest.movie

import com.adriesavana.data.movie.entity.MovieListEntity
import com.adriesavana.data.movie.repository.MovieService
import io.reactivex.Single
import retrofit2.Retrofit

class MovieServiceImpl(private val retrofit: Retrofit) : MovieService {

    private val movieApi by lazy { retrofit.create(MovieApi::class.java) }

    override fun getMovieList(category: String, page: String): Single<MovieListEntity> {
        return movieApi.getListMovie("https://api.themoviedb.org/3/movie/$category?api_key=76bcc538ecfab524df4c50b83b85979a&language=en-US&page=$page")
    }
}