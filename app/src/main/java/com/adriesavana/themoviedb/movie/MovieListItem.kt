package com.adriesavana.themoviedb.movie

import android.view.View
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BindableListItemViewHolder
import com.adriesavana.themoviedb.databinding.ListItemMovieBinding
import com.mikepenz.fastadapter.items.AbstractItem

class MovieListItem(val movieDetail: MovieDetail)
    : AbstractItem<MovieListItem, MovieListItem.ViewHolder>() {
    override fun getType(): Int = hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun getLayoutRes(): Int = R.layout.list_item_movie

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.binding.apply {
            movieModel = movieDetail
            executePendingBindings()
        }
    }

    class ViewHolder(itemView: View) : BindableListItemViewHolder<ListItemMovieBinding>(itemView)
}