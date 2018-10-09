package com.adriesavana.themoviedb.common.di

import com.adriesavana.themoviedb.moviedetail.MovieDetailActivity
import com.adriesavana.themoviedb.moviedetail.MovieDetailActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [(MovieDetailActivityModule::class)])
    abstract fun bindMovieDetailActivity(): MovieDetailActivity
}