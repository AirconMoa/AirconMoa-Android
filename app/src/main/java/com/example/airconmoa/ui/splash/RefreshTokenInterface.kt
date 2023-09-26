package com.example.airconmoa.ui.splash

import com.example.airconmoa.ui.login_user.model.LoginResponseData

interface RefreshTokenInterface {

    fun onPostRefreshJwtSuccess(data : LoginResponseData)

    fun onPostRefreshJwtFailure(message : String)
}