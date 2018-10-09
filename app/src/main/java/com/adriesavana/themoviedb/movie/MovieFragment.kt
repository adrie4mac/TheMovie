package com.adriesavana.themoviedb.movie

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefreshed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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