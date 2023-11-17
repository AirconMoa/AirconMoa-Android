package com.example.airconmoa.ui.login_company.data

import com.google.gson.annotations.SerializedName

data class CompanyLoginResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String,
)