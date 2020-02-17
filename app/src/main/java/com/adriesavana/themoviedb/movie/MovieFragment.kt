package com.adriesavana.themoviedb.movie

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import com.adriesavana.kit.extension.disposedBy
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BaseListFragment
import com.adriesavana.themoviedb.moviedetail.MovieDetailActivity
import com.adriesavana.themoviedb.utils.start
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseListFragment<MovieFragmentViewModelType>(), MovieListItem.OnEventClickListener {

    override fun getLayoutRes(): Int = R.layout.fragment_movie

    override fun getRecyclerView(): ViewGroup = movieRecyclerView

    override fun getSwipeRefreshLayout(): ViewGroup = movieSwipeRefreshLayout

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun doLoadMore() {
        viewModel.inputs.loadMovie()
    }

    override fun onRefreshed() {
        itemAdapter.clear()
        viewModel.inputs.loadMovie(1)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.outputs.onLoadMovie
                .doOnNext {
                    removeLoadMore()
                    stopRefreshing()
                }
                .map { t -> t.asSequence().map { MovieListItem(this, it) }.toList() }
                .subscribe { t: List<MovieListItem>? -> itemAdapter.add(t) }
                .disposedBy(compositeDisposable)

        viewModel.outputs.showLoadMore
                .subscribe { itemAdapter.add(loadMoreListItem) }
                .disposedBy(compositeDisposable)
    }

    override fun setup() {
        super.setup()
        viewModel.inputs.onViewLoaded(arguments?.getString(EXTRA_CATEGORY))
        itemAdapter.add(loadMoreListItem)
    }

    companion object {
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"

        fun newInstance(category: String): MovieFragment {
            val bundle = Bundle()
            bundle.putString(EXTRA_CATEGORY, category)
            return MovieFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun openDetail(movieDetail: MovieDetail) {
        activity?.start(MovieDetailActivity::class.java) {
            putExtra(MovieDetailActivity.EXTRA_MOVIE, movieDetail)
        }
    }
}