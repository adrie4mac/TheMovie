package com.adriesavana.themoviedb.movie

import android.graphics.Color
import android.os.Bundle
import com.adriesavana.themoviedb.R
import com.adriesavana.themoviedb.common.adapter.ViewPagerAdapter
import com.adriesavana.themoviedb.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : BaseActivity<MovieViewModelType>() {

    companion object {
        private const val CATEGORY_POPULAR = "POPULAR"
        private const val CATEGORY_TOP_RATED = "TOP RATED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieFragment.newInstance(CATEGORY_POPULAR.toLowerCase()), CATEGORY_POPULAR)
        adapter.addFragment(MovieFragment.newInstance(CATEGORY_TOP_RATED), CATEGORY_TOP_RATED)
        viewPager_movie.adapter = adapter
        tabs_movie.setupWithViewPager(viewPager_movie)
        tabs_movie.setTabTextColors(Color.parseColor("#66ffffff"), Color.parseColor("#FFFFFF"));
    }
}
