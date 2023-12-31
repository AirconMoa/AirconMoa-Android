package com.example.airconmoa.ui.home

import com.example.airconmoa.config.BaseResponse
import retrofit2.http.GET

interface HomeRetrofitInterface {

    @GET("/api/user/company-info")
    suspend fun getCompanyInfo(): BaseResponse<List<CompanyItemData>>

}