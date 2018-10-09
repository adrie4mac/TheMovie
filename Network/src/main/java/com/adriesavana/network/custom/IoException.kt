package com.adriesavana.network.custom

import com.adriesavana.data.movie.model.ErrorEntity
import com.adriesavana.kit.model.Error
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class IoException(private val errorMessage: String?,
                  private val code: Int) : IOException(errorMessage) {

    fun fetchErrorMessage():String {
        return errorMessage ?: localizedMessage
    }

    fun code() = code

    companion object {
        fun fetchErrorMessage(e: HttpException): String {
            var errorMessage = "${e.message}"
            var errorModel: Error? = null

            try {
                val responseString: String = e.response().errorBody()?.bytes()?.let { String(it) } ?: ""
                errorModel = Gson().fromJson(responseString, ErrorEntity::class.java).toError()
            } catch (e: Exception) {
            }

            errorModel?.errorMessage?.apply {
                errorMessage = this
            }

            return errorMessage
        }
    }

}