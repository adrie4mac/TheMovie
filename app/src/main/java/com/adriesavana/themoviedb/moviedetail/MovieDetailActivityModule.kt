package com.adriesavana.themoviedb.moviedetail

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class MovieDetailActivityModule {

    @Provides
    fun provideViewModel(activity: MovieDetailActivity, factory: MovieDetailViewModel.Factory)
            : MovieDetailViewModelType {
        return ViewModelProviders.of(activity, factory).get(MovieDetailViewModel::class.java)
    }
}