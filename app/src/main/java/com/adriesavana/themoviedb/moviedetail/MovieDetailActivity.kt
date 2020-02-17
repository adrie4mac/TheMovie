package com.adriesavana.themoviedb.moviedetail

import android.os.Bundle
import com.adriesavana.kit.extension.disposedBy
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BaseActivity
import com.adriesavana.themoviedb.databinding.ActivityMovieDetailBinding
import com.adriesavana.themoviedb.utils.toolbarTransparent
import com.android.databinding.library.baseAdapters.BR

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModelType>() {

    companion object {
        const val TAG = "MovieDetailActivity"
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    override fun getLayoutRes(): Int = R.layout.activity_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        toolbarTransparent()
        super.onCreate(savedInstanceState)

        val movieDetail = intent.extras.get(EXTRA_MOVIE) as? MovieDetail
        viewModel.inputs.onViewLoaded(movieDetail)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.outputs.onLoadMovieDetail.subscribe {
            binding.apply {
                setVariable(BR.movieModel, it)
                executePendingBindings()
            }
        }.disposedBy(compositeDisposable)
    }
}
