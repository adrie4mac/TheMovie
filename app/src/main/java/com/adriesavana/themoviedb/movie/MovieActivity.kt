package com.adriesavana.themoviedb.movie

import android.os.Bundle
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BaseActivity
import com.adriesavana.themoviedb.moviedetail.MovieDetailViewModelType

class MovieActivity : BaseActivity<MovieActivityViewModelType>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }

    override fun bindViewModel() {
        super.bindViewModel()
    }
}
