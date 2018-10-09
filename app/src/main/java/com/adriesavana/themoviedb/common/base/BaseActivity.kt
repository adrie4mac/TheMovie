package com.adriesavana.themoviedb.common.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

interface ViewModelType

abstract class BaseActivity<VM : ViewModelType> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModel: VM
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        bindViewModel()
    }

    open fun bindViewModel() {

    }

    @CallSuper
    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}