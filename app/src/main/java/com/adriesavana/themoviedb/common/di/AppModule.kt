package com.adriesavana.themoviedb.common.di

import android.app.Application
import com.adriesavana.kit.constants.TagInjectConstant
import com.adriesavana.themoviedb.BuildConfig
import com.adriesavana.themoviedb.common.base.ConnectivityInterceptor
import com.adriesavana.themoviedb.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @Named(TagInjectConstant.IS_DEBUG)
    fun provideIsDebug() = BuildConfig.DEBUG

    @Provides
    @Named(TagInjectConstant.SCHEDULER_EXECUTION)
    fun provideExecutionScheduler() = Schedulers.io()

    @Provides
    @Named(TagInjectConstant.SCHEDULER_POST_EXECUTION)
    fun providePostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideNetworkUtil(app: Application): NetworkUtil {
        return NetworkUtil(app.applicationContext)
    }

    @Provides
    @Singleton
    @Named(TagInjectConstant.CONNECTIVITY_INTERCEPTOR)
    fun provideConnectivityInterceptor(networkUtil: NetworkUtil)
            : Interceptor {
        return ConnectivityInterceptor(networkUtil, BuildConfig.KONEKSI_INTERNET_TERPUTUS, BuildConfig.TERJADI_KESALAHAN)
    }

    @Provides
    @Singleton
    @Named(TagInjectConstant.APP_INIT_URL)
    fun provideAppInitUrl() = BuildConfig.APP_INIT_URL

    @Provides
    @Singleton
    @Named(TagInjectConstant.API_KEY)
    fun provideAppApiKey() = BuildConfig.API_KEY

}