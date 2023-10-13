package com.example.airconmoa.ui.login_user


import com.example.airconmoa.ui.login_user.model.LoginPostData
import com.example.airconmoa.ui.login_user.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {

    @POST("/api/auth/login")
    fun postLogin(@Body params : LoginPostData) : Call<LoginResponse>
}