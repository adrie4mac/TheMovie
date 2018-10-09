package com.adriesavana.data.movie.entity

import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.movie.model.MovieList
import com.google.gson.annotations.SerializedName

class MovieListEntity(@SerializedName("page") val requestId: Int? = null,
                      @SerializedName("total_results") val totalResults: Int? = null,
                      @SerializedName("total_pages") val totalPages: Int? = null,
                      @SerializedName("results") val movies: List<MovieEntity>? = null) {

    fun createMovieList() : MovieList {
        val movieList: MutableList<MovieDetail> = mutableListOf()

        movies?.forEach {
            movieList.add(MovieDetail(
                    voteCount = it.voteCount ?: -1,
                    id = it.id ?: -1,
                    video = it.video ?: false,
                    voteAverage = it.voteAverage ?: 0f,
                    title = it.title ?: "",
                    popularity = it.popularity ?: 0f,
                    posterPath = it.posterPath ?: "",
                    originalLanguage = it.originalLanguage ?: "",
                    originalTitle = it.originalTitle ?: "",
                    genreIds = it.genreIds,
                    backdropPath = it.backdropPath ?: "",
                    adult = it.adult ?: false,
                    overview = it.overview ?: "",
                    releaseDate = it.releaseDate ?: ""
                    ))
        }

        return MovieList(totalPagesOfMoview = totalPages ?: -1, movieList = movieList)
    }
}