package com.example.airconmoa.ui.join_company.data

import android.app.Application
import com.example.airconmoa.config.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyJoinDataSource {

    private val api = App.retrofit.create(RetrofitInterface::class.java)


    fun join(join: Join, companyJoinView: CompanyJoinView){
        api.companyjoin(join).enqueue(object : Callback<CompanyJoinResponse>{
            override fun onResponse(
                call: Call<CompanyJoinResponse>,
                response: Response<CompanyJoinResponse>
            ) {
                if(response.isSuccessful && response.code() == 200){
                    val companyJoinResponse: CompanyJoinResponse = response.body()!!
                    when(val code = companyJoinResponse.code){
                        200-> companyJoinView.onLoginSuccess(code,companyJoinResponse.result!!)
                        else -> companyJoinView.onLoginFailure(response.message())
                    }
                }
            }

            override fun onFailure(call: Call<CompanyJoinResponse>, t: Throwable)
            = companyJoinView.onLoginFailure(t.message)

        })
    }

}