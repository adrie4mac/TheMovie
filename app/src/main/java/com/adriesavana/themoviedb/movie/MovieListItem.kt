package com.adriesavana.themoviedb.movie

import android.view.View
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.base.BindableListItemViewHolder
import com.adriesavana.themoviedb.databinding.ListItemMovieBinding
import com.mikepenz.fastadapter.items.AbstractItem

class MovieListItem(private val eventListener: OnEventClickListener?,
                    private val movieDetail: MovieDetail)
    : AbstractItem<MovieListItem, MovieListItem.ViewHolder>() {

    private val voteAverage by lazy {
        movieDetail.voteAverage.toString()
    }

    private val titleMovie by lazy {
        "${movieDetail.title} (${movieDetail.releaseDate.take(4)})"
    }

    override fun getType(): Int = hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun getLayoutRes(): Int = R.layout.list_item_movie

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.binding.apply {
            listener = eventListener
            movieModel = movieDetail
            vote = voteAverage
            title = titleMovie
            executePendingBindings()
        }
    }

    class ViewHolder(itemView: View) : BindableListItemViewHolder<ListItemMovieBinding>(itemView)

    interface OnEventClickListener {
        fun openDetail(movieDetail: MovieDetail)
    }
}
