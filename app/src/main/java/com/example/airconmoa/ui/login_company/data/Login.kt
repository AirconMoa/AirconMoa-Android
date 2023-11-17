package com.example.airconmoa.ui.login_company.data

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String
)