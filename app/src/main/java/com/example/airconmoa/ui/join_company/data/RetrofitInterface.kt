package com.example.airconmoa.ui.join_company.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitInterface {

    @POST("/api/company/signup")
    fun companyjoin(@Body join: Join): Call<CompanyJoinResponse>

}