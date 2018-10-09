package com.adriesavana.themoviedb.moviedetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adriesavana.movie.model.MovieList
import com.adriesavana.movie.usecase.GetMovieListUseCase
import com.adriesavana.themoviedb.common.base.ViewModelType
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MovieDetailViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onViewLoaded()
    }

    interface Outputs {
        val errorMessage: Observable<String>
    }
}

class MovieDetailViewModel(private val movieUseCase: GetMovieListUseCase):
        ViewModel(),
        MovieDetailViewModelType,
        MovieDetailViewModelType.Inputs,
        MovieDetailViewModelType.Outputs {

    private val errorSubject = PublishSubject.create<String>()

    override val inputs: MovieDetailViewModelType.Inputs get() = this

    override val outputs: MovieDetailViewModelType.Outputs get() = this

    override val errorMessage: Observable<String> get() = errorSubject.filter { it.isNotEmpty() }

    override fun onViewLoaded() {
        movieUseCase.execute(GetMovieListObserver(), GetMovieListUseCase.Params("popular", 1))
    }

    override fun onCleared() {
        movieUseCase.dispose()
        super.onCleared()
    }

    inner class GetMovieListObserver : DisposableSingleObserver<MovieList>() {
        override fun onSuccess(t: MovieList) {

        }

        override fun onError(e: Throwable) {

        }
    }

    class Factory
    @Inject constructor(private val movieUseCase: GetMovieListUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel(movieUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
