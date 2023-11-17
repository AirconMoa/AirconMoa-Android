package com.example.airconmoa.ui.login_company.data

import android.util.Log
import com.example.airconmoa.config.App
import com.example.airconmoa.ui.join_company.data.CompanyJoinResponse
import com.example.airconmoa.ui.join_company.data.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyLoginDataSource {

    private val api = App.retrofit.create(LoginRetrofitinterface::class.java)

    fun login(login: Login, companyLoginView: CompanyLoginView){
        api.companylogin(login).enqueue(object : Callback<CompanyLoginResponse> {
            override fun onResponse(
                call: Call<CompanyLoginResponse>,
                response: Response<CompanyLoginResponse>
            ) {
                Log.d("Tester", "onResponse: api진입${response.message()}")
                if(response.isSuccessful && response.code() == 200){
                    Log.d("Tester", "onResponse: if문 진입${response.code()}")
                    val companyLoginResponse: CompanyLoginResponse = response.body()!!
                    Log.d("Tester", "onResponse: ${companyLoginResponse.code}")
                    when(val code = companyLoginResponse.code){
                        1000-> companyLoginView.onLoginSuccess(code,companyLoginResponse.result!!)
                        else -> companyLoginView.onLoginFailure(response.message())
                    }
                }
            }

            override fun onFailure(call: Call<CompanyLoginResponse>, t: Throwable)
            = companyLoginView.onLoginFailure(t.message)


        })
    }

}