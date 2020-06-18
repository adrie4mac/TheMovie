package com.adriesavana.kit.model

open class Error(
        val requestId: String,
        val status: Int,
        val data: List<Any>,
        val errorMessage: String?,
        val serverProcessTime: Double,
        val app: String)