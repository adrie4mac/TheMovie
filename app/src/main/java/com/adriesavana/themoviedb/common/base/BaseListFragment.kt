package com.adriesavana.themoviedb.common.base

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.adriesavana.kit.extension.disposedBy
import com.adriesavana.themoviedb.common.item.LoadMoreListItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter

abstract class BaseListFragment<VM : ViewModelType> : BaseFragment<VM>() {

    protected var itemAdapter = ItemAdapter<IItem<*, *>>()
    protected var fastAdapter: FastAdapter<IItem<*, *>> = FastAdapter.with(itemAdapter)
    protected var loadMoreListItem: LoadMoreListItem = LoadMoreListItem()

    protected lateinit var recyclerView: RecyclerView
    protected lateinit var swipeRefreshLayout: SwipeRefreshLayout
    protected lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    protected lateinit var scrollToTop: ImageView
    protected var itemPadding: Int = 0
    protected var scrollToTopTranslation: Float = 0F

    //region override method
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBindView()
        bindViewModel()
        setup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.let {
            it.adapter = null
            it.layoutManager = null
        }
        swipeRefreshLayout.setOnRefreshListener(null)
    }
    //endregion

    //region private method
    private fun initView() {
        recyclerView = getRecyclerView() as RecyclerView
        swipeRefreshLayout = getSwipeRefreshLayout() as SwipeRefreshLayout
        staggeredGridLayoutManager = getLayoutManager() as StaggeredGridLayoutManager
    }

    private fun initBindView() {
        loadMoreListItem.onLoadMore
                .filter { it }
                .subscribe { doLoadMore() }
                .disposedBy(compositeDisposable)

        swipeRefreshLayout.apply {
            isRefreshing = true
            setOnRefreshListener {
                onRefreshed()
            }
        }

        recyclerView.apply {
            layoutManager = staggeredGridLayoutManager
            itemAnimator = null
            adapter = fastAdapter
            setHasFixedSize(true)
        }
    }

    open fun removeLoadMore() {
        if (itemAdapter.adapterItems.contains(loadMoreListItem)) {
            itemAdapter.remove(itemAdapter.getAdapterPosition(loadMoreListItem))
        }
    }

    open fun stopRefreshing() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    open fun bindViewModel() {}

    open fun setup() {}

    protected abstract fun getLayoutRes(): Int

    protected abstract fun getRecyclerView(): ViewGroup

    protected abstract fun getSwipeRefreshLayout(): ViewGroup

    protected abstract fun getLayoutManager(): RecyclerView.LayoutManager

    protected abstract fun doLoadMore()

    protected abstract fun onRefreshed()

    //endregion

}
