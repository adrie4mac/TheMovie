package com.adriesavana.data.movie.model

import com.adriesavana.kit.model.Error
import com.google.gson.annotations.SerializedName

data class ErrorEntity(
        @SerializedName("request_id") val requestId: String?,
        @SerializedName("status") val status: Int?,
        @SerializedName("data") val data: List<Any>?,
        @SerializedName("error_message") val errorMessage: String? = null,
        @SerializedName("server_process_time") val serverProcessTime: Double?,
        @SerializedName("app") val app: String?) {

    fun toError(): Error {
        return Error(requestId ?: "",
                status ?: 0,
                data ?: listOf(),
                errorMessage ?: "",
                serverProcessTime ?: 0.0,
                app ?: "")
    }
}