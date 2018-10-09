package com.adriesavana.movie.usecase

import com.adriesavana.kit.base.UseCase
import com.adriesavana.kit.constants.TagInjectConstant
import com.adriesavana.movie.model.MovieList
import com.adriesavana.movie.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class GetMovieListUseCase
@Inject constructor(
        private val repository: MovieRepository,
        @Named(TagInjectConstant.SCHEDULER_EXECUTION) threadException: Scheduler,
        @Named(TagInjectConstant.SCHEDULER_POST_EXECUTION) postExecutionThread: Scheduler)
    : UseCase.RxSingle<MovieList, GetMovieListUseCase.Params>(threadException, postExecutionThread) {
    override fun build(params: GetMovieListUseCase.Params?): Single<MovieList> {
        params?.let {
            return repository.getMovieList(it.category!!, it.page.toString())
                    .doOnSuccess { params.page = ++params.page }
        }

        throw RuntimeException("param movie by keyword not available")
    }

    data class Params(var category: String = "", var page: Int = 1)
}
