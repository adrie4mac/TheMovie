package com.adriesavana.data.movie

import com.adriesavana.data.movie.source.MovieDataSource
import com.adriesavana.movie.model.MovieList
import com.adriesavana.movie.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositorySource
@Inject constructor(private val factory: MovieDataSource.Factory) : MovieRepository {

    override fun getMovieList(category: String, page: String): Single<MovieList> {
        return factory.network().getMovieList(category, page)
    }
}