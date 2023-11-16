package com.example.airconmoa.config

data class BaseResponse<T>(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: T?,
)

