package com.example.airconmoa.ui.join_user

import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.ui.join_user.model.PostOauthLoginRes
import com.example.airconmoa.ui.join_user.model.PostSignUpReq
import com.example.airconmoa.ui.join_user.model.PostUidDeviceTokenReq
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface JoinRetrofitInterface {
    @POST("/api/user/signup")
    suspend fun signUp(@Body postSignUpReq: PostSignUpReq): BaseResponse<PostOauthLoginRes>

    @POST("/api/user/oauth/device-token")
    suspend fun saveUidAndToken(
        @Header("Authorization") accessToken: String,
        @Body postUidDeviceTokenReq: PostUidDeviceTokenReq,
    ): BaseResponse<String>
}