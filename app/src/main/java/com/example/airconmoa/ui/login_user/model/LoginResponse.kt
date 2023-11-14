package com.example.airconmoa.ui.login_user.model

import com.example.airconmoa.config.BaseResponse

//data class LoginResponse(
//    val data : LoginResponseData
//) : BaseResponse()

data class LoginResponseData(
    val accessToken : String,
    val refreshToken : String,
    val accessTokenExpiredDate : String,
    val refreshTokenExpiredDate : String
)
