package com.adriesavana.themoviedb.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adriesavana.movie.model.MovieDetail
import com.adriesavana.movie.model.MovieList
import com.adriesavana.movie.usecase.GetMovieListUseCase
import com.adriesavana.network.extension.getErrorMessage
import com.adriesavana.themoviedb.common.base.ViewModelType
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MovieFragmentViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onViewLoaded()
    }

    interface Outputs {
        val errorMessage: Observable<String>
        val onLoadMovie: Observable<List<MovieDetail>>
    }

}

class MovieFragmentViewModel(private val movieUseCase: GetMovieListUseCase):
        ViewModel(),
        MovieFragmentViewModelType,
        MovieFragmentViewModelType.Inputs,
        MovieFragmentViewModelType.Outputs {

    override val inputs: MovieFragmentViewModelType.Inputs get() = this

    override val outputs: MovieFragmentViewModelType.Outputs get() = this

    private val errorSubject = PublishSubject.create<String>()

    private val movieListSubject = PublishSubject.create<List<MovieDetail>>()

    override fun onViewLoaded() {
        movieUseCase.execute(GetMovieListObserver(), GetMovieListUseCase.Params("popular"))
    }

    override val errorMessage: Observable<String> get() = errorSubject.filter { it.isNotEmpty() }

    override val onLoadMovie: Observable<List<MovieDetail>> get() = movieListSubject

    inner class GetMovieListObserver : DisposableSingleObserver<MovieList>() {
        override fun onSuccess(t: MovieList) {
            movieListSubject.onNext(t.movieList)
        }

        override fun onError(e: Throwable) {
            errorSubject.onNext(e.getErrorMessage())
        }
    }

    class Factory
    @Inject constructor(private val movieUseCase: GetMovieListUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieFragmentViewModel::class.java)) {
                return MovieFragmentViewModel(movieUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}