package com.example.airconmoa.ui.splash

import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.ui.join_user.model.PostUidDeviceTokenReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SplashRetrofitInterface {

    @GET("/api/user/check-token")
    suspend fun isTokenExpired(@Header("Authorization") accessToken : String): BaseResponse<Boolean>

}