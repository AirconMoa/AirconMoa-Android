package com.example.airconmoa.ui.estimate_company.model

import com.google.gson.annotations.SerializedName

data class GetRequestEstimateRes(

    @SerializedName("requestEstimateId")
    val requestEstimateId : Long,

    @SerializedName("porfileUrl")
    val porfileUrl : String,

    @SerializedName("userNickname")
    val userNickname : String,

    @SerializedName("installInfo")
    val installInfo : String,

    @SerializedName("installationDate")
    val installationDate : String
)
