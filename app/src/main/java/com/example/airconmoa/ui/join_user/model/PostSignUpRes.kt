package com.example.airconmoa.ui.join_user.model

import com.google.gson.annotations.SerializedName

data class PostSignUpRes(
    @SerializedName("userNickName")
    val userNickName : String,

    @SerializedName("userEmail")
    val userEmail : String,

    @SerializedName("userId")
    val userId : Long
)
