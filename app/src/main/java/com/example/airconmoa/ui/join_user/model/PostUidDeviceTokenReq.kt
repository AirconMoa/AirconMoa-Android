package com.example.airconmoa.ui.join_user.model

import com.google.gson.annotations.SerializedName

data class PostUidDeviceTokenReq(
    @SerializedName("uid")
    val uid : String,

    @SerializedName("deviceToken")
    val deviceToken : String
)
