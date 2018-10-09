package com.adriesavana.data.movie.di

import com.adriesavana.data.movie.MovieRepositorySource
import com.adriesavana.movie.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(source: MovieRepositorySource): MovieRepository = source
}