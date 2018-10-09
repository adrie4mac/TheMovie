package com.adriesavana.themoviedb.movie

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class MovieFragmentModule {

    @Provides
    fun provideViewModel(fragment: MovieFragment, factory: MovieFragmentViewModel.Factory)
            : MovieFragmentViewModelType {
        return ViewModelProviders.of(fragment, factory).get(MovieFragmentViewModel::class.java)
    }
}