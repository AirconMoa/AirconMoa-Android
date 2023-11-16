package com.example.airconmoa.ui.login_user.model

//data class LoginResponse(
//    val data : LoginResponseData
//) : BaseResponse()

data class LoginResponseData(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredDate: String,
    val refreshTokenExpiredDate: String,
)
