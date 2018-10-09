package com.adriesavana.themoviedb.moviedetail

import android.os.Bundle
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BaseActivity

class MovieDetailActivity : BaseActivity<MovieDetailViewModelType>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        viewModel.inputs.onViewLoaded()
    }
}
