package com.adriesavana.themoviedb.app

import android.app.Activity
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.adriesavana.themoviedb.common.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MovieDBApplication : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}