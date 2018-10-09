package com.adriesavana.movie.model

data class MovieList(var isPagination: Boolean = false,
                     var nextPage: Int = -1,
                     val totalPagesOfMoview: Int,
                     val movieList: List<MovieDetail>)