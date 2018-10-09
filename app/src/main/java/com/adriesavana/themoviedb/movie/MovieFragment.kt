package com.adriesavana.themoviedb.movie

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import com.adriesavana.kit.extension.disposedBy
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BaseListFragment
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseListFragment<MovieFragmentViewModelType>() {

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
        viewModel.inputs.loadMovie(1)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.outputs.onLoadMovie
                .doOnNext {
                    removeLoadMore()
                    stopRefreshing()
                }
                .subscribe { t: List<MovieDetail>? -> t?.forEach {
            System.out.println(it.title)
        }}.disposedBy(compositeDisposable)
    }

    override fun setup() {
        super.setup()
        viewModel.inputs.onViewLoaded(arguments?.getString(EXTRA_CATEGORY))
        itemAdapter.add(loadMoreListItem)
    }

    companion object {
        const val TAG = "MovieFragment"
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"

        fun newInstance(category: String): MovieFragment {
            val fragment = MovieFragment()
            val args = Bundle()
            args.putString(EXTRA_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }
}