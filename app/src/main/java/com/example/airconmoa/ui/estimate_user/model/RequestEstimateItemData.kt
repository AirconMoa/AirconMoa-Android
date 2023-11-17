package com.example.airconmoa.ui.estimate_user.model

import com.google.gson.annotations.SerializedName

data class RequestEstimateItemData(
    @SerializedName("installInfo")
    val installInfo: String?,

    @SerializedName("installAddress")
    val installAddress: String,

    @SerializedName("buildingType")
    val buildingType: String,

    @SerializedName("amount")
    val amount: Int,

    @SerializedName("installationDate")
    val installationDate: String,

    @SerializedName("brand")
    val brand: String
)