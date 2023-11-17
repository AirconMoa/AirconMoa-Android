package com.example.airconmoa.ui.estimate_user

import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.ui.estimate_user.model.RequestEstimateItemData
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface EstimateWriteUserRetrofitInterface {

    @POST("/api/request-estimate")
    suspend fun postWriteEstimate(
        @Header("Authorization") accessToken : String,
        @Body requestEstimateItemData:RequestEstimateItemData)
    : BaseResponse<String>

}