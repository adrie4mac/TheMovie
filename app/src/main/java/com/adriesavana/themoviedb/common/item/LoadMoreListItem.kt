package com.adriesavana.themoviedb.common.item

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BindableListItemViewHolder
import com.adriesavana.themoviedb.databinding.ListItemLoadmoreBinding
import com.mikepenz.fastadapter.items.AbstractItem
import io.reactivex.subjects.PublishSubject

class LoadMoreListItem : AbstractItem<LoadMoreListItem, LoadMoreListItem.ViewHolder>() {

    val onLoadMore = PublishSubject.create<Boolean>()!!

    override fun getType(): Int = hashCode()

    override fun getLayoutRes(): Int = R.layout.list_item_loadmore

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun bindView(holder: ViewHolder?, payloads: MutableList<Any>?) {
        super.bindView(holder!!, payloads!!)
        holder.binding.executePendingBindings()

        if (holder.itemView.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            val param = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            param.isFullSpan = true
        }
        onLoadMore.onNext(true)
    }

    override fun unbindView(holder: ViewHolder?) {
        super.unbindView(holder!!)
        onLoadMore.onNext(false)
    }

    class ViewHolder(itemView: View) : BindableListItemViewHolder<ListItemLoadmoreBinding>(itemView)

}