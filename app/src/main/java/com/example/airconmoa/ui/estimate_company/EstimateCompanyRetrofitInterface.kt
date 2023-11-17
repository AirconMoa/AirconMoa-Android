package com.example.airconmoa.ui.estimate_company

import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.ui.estimate_company.model.GetRequestEstimateRes
import com.example.airconmoa.ui.estimate_company.model.PostRequestEstimateReq
import com.example.airconmoa.ui.join_user.model.PostOauthLoginRes
import com.example.airconmoa.ui.join_user.model.PostSignUpReq
import com.example.airconmoa.ui.join_user.model.PostUidDeviceTokenReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface EstimateCompanyRetrofitInterface {

    @GET("/api/company/request-estimate")
    suspend fun getRequestEstimateList(
        @Header("Authorization") accessToken : String
    ) : BaseResponse<List<GetRequestEstimateRes>>

    @GET("/api/request-estimate")
    suspend fun getRequestEstimate(
        @Header("Authorization") accessToken : String,
        @Query("requestEstimateId") requestEstimateId : Long
    ) : BaseResponse<PostRequestEstimateReq>

    @GET("/api/request-estimate/date")
    suspend fun getRequestEstimateDate(
        @Query("requestEstimateId") requestEstimateId : Long
    ) : BaseResponse<String>
}