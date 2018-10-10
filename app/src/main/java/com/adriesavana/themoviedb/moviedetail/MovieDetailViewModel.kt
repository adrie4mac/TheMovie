package com.adriesavana.themoviedb.moviedetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.movie.model.MovieDetailView
import com.adriesavana.movie.model.MovieList
import com.adriesavana.movie.usecase.GetMovieListUseCase
import com.adriesavana.network.extension.getErrorMessage
import com.adriesavana.themoviedb.common.base.ViewModelType
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MovieDetailViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onViewLoaded(movieDetail: MovieDetail?)
    }

    interface Outputs {
        val errorMessage: Observable<String>
        val onLoadMovieDetail: Observable<MovieDetailView>
    }
}

class MovieDetailViewModel:
        ViewModel(),
        MovieDetailViewModelType,
        MovieDetailViewModelType.Inputs,
        MovieDetailViewModelType.Outputs {

    private val errorSubject = PublishSubject.create<String>()

    private val movieDetailSubject = PublishSubject.create<MovieDetailView>()

    override val inputs: MovieDetailViewModelType.Inputs get() = this

    override val outputs: MovieDetailViewModelType.Outputs get() = this

    override val errorMessage: Observable<String> get() = errorSubject.filter { it.isNotEmpty() }

    override val onLoadMovieDetail: Observable<MovieDetailView> get() = movieDetailSubject

    override fun onViewLoaded(movieDetail: MovieDetail?) {
        movieDetail?.let {
            movieDetailSubject.onNext(it.createMovieView())
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    class Factory
    @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
