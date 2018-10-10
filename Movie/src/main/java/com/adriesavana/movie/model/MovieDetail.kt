package com.adriesavana.movie.model

import java.io.Serializable

data class MovieDetail(val voteCount: Int,
                       val id: Int,
                       val video: Boolean,
                       val voteAverage: Float,
                       val title: String,
                       val popularity: Float,
                       val posterPath: String,
                       val originalLanguage: String,
                       val originalTitle: String,
                       val genreIds: List<Int>?,
                       val backdropPath: String,
                       val adult: Boolean,
                       val overview: String,
                       val releaseDate: String)
    : Serializable {

    fun createMovieView() : MovieDetailView {
        return MovieDetailView(
                voteCountView = "$voteCount votes",
                voteAverageView = "$voteAverage/10",
                titleView = title,
                posterPathView = posterPath,
                originalLanguageView = originalLanguage,
                originalTitleView = originalTitle,
                backdropPathView = backdropPath,
                overview = overview,
                releaseDate = releaseDate
        )
    }

}