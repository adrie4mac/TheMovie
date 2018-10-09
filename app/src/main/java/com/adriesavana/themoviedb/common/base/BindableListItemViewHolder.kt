package com.adriesavana.themoviedb.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BindableListItemViewHolder<out B : ViewDataBinding>(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val binding: B = DataBindingUtil.bind(itemView)!!
}
