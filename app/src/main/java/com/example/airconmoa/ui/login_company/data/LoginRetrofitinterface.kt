package com.example.airconmoa.ui.login_company.data

import com.example.airconmoa.ui.join_company.data.CompanyJoinResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitinterface {
    @POST("/api/company/login")
    fun companylogin(@Body login: Login): Call<CompanyLoginResponse>
}