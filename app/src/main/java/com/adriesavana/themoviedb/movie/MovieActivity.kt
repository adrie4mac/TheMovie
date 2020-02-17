package com.adriesavana.themoviedb.movie

import android.graphics.Color
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.adapter.ViewPagerAdapter
import com.adriesavana.themoviedb.common.base.BaseActivity
import com.adriesavana.themoviedb.databinding.ActivityMovieBinding

class MovieActivity : BaseActivity<ActivityMovieBinding, MovieViewModelType>() {

    companion object {
        const val CATEGORY_POPULAR = "POPULAR"
        private const val CATEGORY_TOP_RATED = "TOP RATED"
    }

    override fun getLayoutRes(): Int = R.layout.activity_movie

    override fun bindViewModel() {
        setupViewPager()
        super.bindViewModel()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(MovieFragment.newInstance(CATEGORY_POPULAR.toLowerCase()), CATEGORY_POPULAR)
            addFragment(MovieFragment.newInstance(CATEGORY_TOP_RATED), CATEGORY_TOP_RATED)
        }

        with(binding) {
            viewPagerMovie.adapter = adapter
            tabsMovie.apply {
                setupWithViewPager(viewPagerMovie)
                setTabTextColors(Color.parseColor("#66ffffff"), Color.parseColor("#FFFFFF"))
            }
        }
    }
}
