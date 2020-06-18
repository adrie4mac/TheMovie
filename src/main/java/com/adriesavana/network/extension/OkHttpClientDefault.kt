package com.adriesavana.network.extension

import com.adriesavana.kit.constants.ApiConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

internal fun OkHttpClient.Builder.defaultBuilder(isDebug: Boolean, chuckInterceptor: Interceptor)
        : OkHttpClient.Builder {
    if (isDebug) {
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        addInterceptor(chuckInterceptor)
    }
    connectTimeout(ApiConstant.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
    readTimeout(ApiConstant.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
    writeTimeout(ApiConstant.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
    return this
}
