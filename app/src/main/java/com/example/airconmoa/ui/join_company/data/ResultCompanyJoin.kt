package com.example.airconmoa.ui.join_company.data

import com.google.gson.annotations.SerializedName

data class ResultCompanyJoin(
    @SerializedName("compnayId") val compnayId: Int,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("email") val email: String,
)
