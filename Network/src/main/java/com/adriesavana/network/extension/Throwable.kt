package com.adriesavana.network.extension

import com.adriesavana.kit.constants.ApiConstant
import com.adriesavana.network.custom.IoException
import retrofit2.HttpException

fun Throwable.getErrorMessage(): String {
    return when (this) {
        is IoException -> fetchErrorMessage()
        is HttpException -> IoException.fetchErrorMessage(this)
        else -> "Terjadi kesalahan, silahkan coba lagi"
    }
}

fun Throwable.getErrorCode(): Int {
    return when (this) {
        is IoException -> code()
        is HttpException -> code()
        else -> ApiConstant.HTTP_BAD_REQUEST
    }
}