package com.example.airconmoa.ui.join_company.data

import android.app.Application
import android.util.Log
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
                Log.d("Tester", "onResponse: api진입${response.code()}")
                if(response.isSuccessful && response.code() == 200){
                    Log.d("Tester", "onResponse: if문 진입${response.code()}")
                    val companyJoinResponse: CompanyJoinResponse = response.body()!!
                    Log.d("Tester", "onResponse: ${companyJoinResponse.code}")
                    when(val code = companyJoinResponse.code){
                        1000-> companyJoinView.onLoginSuccess(code,companyJoinResponse.result!!)
                        else -> companyJoinView.onLoginFailure(response.message())
                    }
                }
            }

            override fun onFailure(call: Call<CompanyJoinResponse>, t: Throwable)
            = companyJoinView.onLoginFailure(t.message)

        })
    }

}