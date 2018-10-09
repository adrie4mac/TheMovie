package com.adriesavana.themoviedb.common.di

import android.app.Application
import com.adriesavana.data.movie.di.DataModule
import com.adriesavana.network.di.NetworkModule
import com.adriesavana.themoviedb.app.MovieDBApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (BuilderModule::class),
    (NetworkModule::class),
    (DataModule::class),
    (AppModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MovieDBApplication)
}