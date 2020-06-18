package com.adriesavana.kit.constants

class ApiConstant {

    object QueryValue {
        const val CONTENT_TYPE = "application/vnd.api+json"
    }

    object QueryKey {
        const val AUTHORIZATION = "Authorization"
        const val CONTENT_TYPE = "Content-Type"
        const val BEARER = "Bearer"
        const val SESSION_ID = "session_id"
    }

    companion object {
        const val TIME_OUT_IN_SECONDS: Long = 300
        const val REQUEST_COUNT_LIMIT = 3

        const val HTTP_RESPONSE_UNAUTHORIZED = 401
        const val HTTP_RESPONSE_FORBIDDEN = 403
        const val HTTP_BAD_REQUEST = 400
        const val HTTP_TIME_OUT = 504

        // Success response codes
        const val HTTP_RESPONSE_OK = 200
        const val HTTP_RESPONSE_CREATED = 201
        const val HTTP_RESPONSE_NO_CONTENT = 204
        const val HTTP_RESPONSE_NOT_MODIFIED = 304
    }
}
