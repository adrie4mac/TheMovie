package com.adriesavana.themoviedb.moviedetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.adriesavana.kit.extension.disposedBy
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BaseActivity
import com.adriesavana.themoviedb.databinding.ActivityMovieDetailBinding
import com.android.databinding.library.baseAdapters.BR

class MovieDetailActivity : BaseActivity<MovieDetailViewModelType>() {

    lateinit var binding: ActivityMovieDetailBinding

    companion object {
        const val TAG = "MovieDetailActivity"
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val movieDetail = intent.extras.get(EXTRA_MOVIE) as? MovieDetail
        viewModel.inputs.onViewLoaded(movieDetail)
    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.outputs.onLoadMovieDetail.subscribe{
            binding.apply {
                setVariable(BR.movieModel, it)
                executePendingBindings()
            }
        }.disposedBy(compositeDisposable)
    }
}
