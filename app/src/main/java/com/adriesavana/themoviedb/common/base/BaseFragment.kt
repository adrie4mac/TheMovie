package com.adriesavana.themoviedb.common.base

import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<VM: ViewModelType> : Fragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModel: VM
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    protected val compositeDisposable = CompositeDisposable()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
