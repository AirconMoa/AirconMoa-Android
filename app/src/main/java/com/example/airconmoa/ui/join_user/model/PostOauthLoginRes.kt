package com.example.airconmoa.ui.join_user.model

import com.google.gson.annotations.SerializedName

data class PostOauthLoginRes(
    @SerializedName("userId")
    val userId: Long,

    @SerializedName("email")
    val email: String,

    @SerializedName("accessToken")
    val accessToken: String,
)
