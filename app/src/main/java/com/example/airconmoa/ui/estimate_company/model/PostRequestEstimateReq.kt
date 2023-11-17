package com.example.airconmoa.ui.estimate_company.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PostRequestEstimateReq (
    @SerializedName("installInfo")
    val installInfo: String,

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
