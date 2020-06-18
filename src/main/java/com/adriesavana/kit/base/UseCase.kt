package com.adriesavana.kit.base

import com.adriesavana.kit.extension.disposedBy
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableSingleObserver

abstract class UseCase<out Type, in Params> protected constructor(
        private val threadExecutor: Scheduler,
        private val postExecutionThread: Scheduler) {

    protected val disposables = CompositeDisposable()

    abstract fun build(params: Params?): Type

    fun dispose() = disposables.dispose()

    abstract class RxSingle<T, in P> protected constructor(
            private val threadExecutor: Scheduler,
            private val postExecutionThread: Scheduler)
        : UseCase<Single<T>, P>(threadExecutor, postExecutionThread) {
        fun execute(observer: DisposableSingleObserver<T>, params: P? = null) {
            build(params)
                    .subscribeOn(threadExecutor)
                    .observeOn(postExecutionThread)
                    .subscribeWith(observer)
                    .disposedBy(disposables)
        }
    }

    abstract class RxCompletable<in P> protected constructor(
            private val threadExecutor: Scheduler,
            private val postExecutionThread: Scheduler)
        : UseCase<Completable, P>(threadExecutor, postExecutionThread) {
        fun execute(observer: DisposableCompletableObserver, params: P? = null) {
            build(params)
                    .subscribeOn(threadExecutor)
                    .observeOn(postExecutionThread)
                    .subscribeWith(observer)
                    .disposedBy(disposables)
        }
    }

    abstract class RxMaybe<T, in P> protected constructor(
            private val threadExecutor: Scheduler,
            private val postExecutionThread: Scheduler)
        : UseCase<Maybe<T>, P>(threadExecutor, postExecutionThread) {
        fun execute(observer: DisposableMaybeObserver<T>, params: P? = null) {
            build(params)
                    .subscribeOn(threadExecutor)
                    .observeOn(postExecutionThread)
                    .subscribeWith(observer)
                    .disposedBy(disposables)
        }
    }
}
