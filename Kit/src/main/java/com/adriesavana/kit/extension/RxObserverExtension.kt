package com.adriesavana.kit.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableSingleObserver

fun <T> DisposableMaybeObserver<T>.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> DisposableSingleObserver<T>.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun DisposableCompletableObserver.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Disposable.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}
