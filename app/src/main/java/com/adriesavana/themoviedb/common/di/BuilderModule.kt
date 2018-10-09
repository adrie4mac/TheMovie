package com.adriesavana.themoviedb.common.di

import com.adriesavana.themoviedb.movie.MovieActivity
import com.adriesavana.themoviedb.movie.MovieActivityModule
import com.adriesavana.themoviedb.movie.MovieFragment
import com.adriesavana.themoviedb.movie.MovieFragmentModule
import com.adriesavana.themoviedb.moviedetail.MovieDetailActivity
import com.adriesavana.themoviedb.moviedetail.MovieDetailActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [(MovieDetailActivityModule::class)])
    abstract fun bindMovieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector(modules = [(MovieActivityModule::class)])
    abstract fun bindMovieActivity(): MovieActivity

    @ContributesAndroidInjector(modules = [(MovieFragmentModule::class)])
    abstract fun bindMovieFragment(): MovieFragment
}