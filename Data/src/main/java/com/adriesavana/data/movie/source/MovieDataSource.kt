package com.adriesavana.data.movie.source

import com.adriesavana.data.movie.repository.MovieService
import com.adriesavana.movie.model.MovieList
import com.adriesavana.movie.repository.MovieRepository
import dagger.Lazy
import io.reactivex.Single
import javax.inject.Inject

interface MovieDataSource : MovieRepository {

    class Factory
    @Inject constructor(private val network: Lazy<Network>) {
        fun network(): Network = network.get()
    }

    class Network
    @Inject constructor(private val movieApi: MovieService) : MovieDataSource {
        override fun getMovieList(category: String, page: String): Single<MovieList> {
            return movieApi.getMovieList(category, page).map { it.createMovieList() }
        }
    }
}