package com.adriesavana.network.di

import com.adriesavana.data.movie.repository.MovieService
import com.adriesavana.kit.constants.TagInjectConstant
import com.adriesavana.network.extension.defaultBuilder
import com.adriesavana.network.rest.movie.MovieServiceImpl
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(TagInjectConstant.RETROFIT_DEFAULT)
    fun provideRetrofit(@Named(TagInjectConstant.OKHTTP_DEFAULT) okHttpClient: OkHttpClient,
                        @Named(TagInjectConstant.APP_INIT_URL) baseUrl: String)
            : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    @Named(TagInjectConstant.OKHTTP_DEFAULT)
    fun provideOkHttpClient(certificatePinner: CertificatePinner,
                            @Named(TagInjectConstant.IS_DEBUG) isDebug: Boolean,
                            @Named(TagInjectConstant.CONNECTIVITY_INTERCEPTOR) connectivityInterceptor: Interceptor)
            : OkHttpClient {
        return OkHttpClient.Builder()
                .defaultBuilder(isDebug)
                .certificatePinner(certificatePinner)
                .addInterceptor(connectivityInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(@Named(TagInjectConstant.RETROFIT_DEFAULT) retrofit: Retrofit,
                            @Named(TagInjectConstant.API_KEY) apiKey: String): MovieService {
        return MovieServiceImpl(retrofit, apiKey)
    }

    /**
     * @see <https://inthecheesefactory.com/blog/retrofit-2.0/en>
     */
    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
                .build()
    }

}