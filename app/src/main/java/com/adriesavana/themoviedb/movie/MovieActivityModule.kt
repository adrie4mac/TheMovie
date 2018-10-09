package com.adriesavana.themoviedb.movie

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class MovieActivityModule {

    @Provides
    fun provideViewModel(activity: MovieActivity, factory: MovieViewModel.Factory)
            : MovieViewModelType {
        return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
    }
}