package com.example.airconmoa.ui.home

import com.google.gson.annotations.SerializedName

data class CompanyItemData(
    @SerializedName("email")
    val email: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("companyImgUrl")
    val companyImgUrl: String,

    @SerializedName("companyAddress")
    val companyAddress: String,
)