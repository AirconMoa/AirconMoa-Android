package com.example.airconmoa.ui.join_company.data

import com.google.gson.annotations.SerializedName

data class Join(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String,
    @SerializedName(value = "companyName")val companyName: String,
    @SerializedName(value = "companyNumber")val companyNumber: String,
    @SerializedName(value = "address")val address: String,
)
