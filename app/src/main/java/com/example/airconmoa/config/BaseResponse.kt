package com.example.airconmoa.config

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: T?
)

