package com.example.airconmoa.ui.join_user.model

import com.google.gson.annotations.SerializedName

data class PostSignUpReq(
    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("authType")
    val authType: String,
)
