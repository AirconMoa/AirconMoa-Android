package com.example.airconmoa.ui.splash

import com.example.airconmoa.ui.login_user.model.LoginResponse
import com.example.airconmoa.ui.splash.model.RefreshJwtPostData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenRetrofitInterface {

    @POST("/api/auth/token/access")
    fun refreshJwt(
        @Body params : RefreshJwtPostData
    ) : Call<LoginResponse>
}