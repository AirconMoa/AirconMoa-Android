package com.example.airconmoa.ui.login_user

import com.example.airconmoa.ui.login_user.model.LoginResponseData

interface LoginActivityInterface {

    fun onPostLoginSuccess(result : LoginResponseData)

    fun onPostLoginFailure(message : String, uuid : String)

}