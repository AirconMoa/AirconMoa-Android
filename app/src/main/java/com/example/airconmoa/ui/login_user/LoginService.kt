package com.example.airconmoa.ui.login_user



import com.example.airconmoa.config.App
import com.example.airconmoa.ui.login_user.model.LoginPostData
import com.example.airconmoa.ui.login_user.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view : LoginActivityInterface) {

    fun postLogin(body : LoginPostData, accessToken : String){
        val loginRetro = App.getRetro().create(LoginRetrofitInterface::class.java)
        loginRetro.postLogin(body).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let{
                    if(response.code() == 200) view.onPostLoginSuccess(it.data)
                    else view.onPostLoginFailure("최초가입 사용자",body.authId)
                }
                if(response.body()==null){
                    view.onPostLoginFailure("최초가입 사용자",body.authId)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure("네트워크 오류", "")
            }
        })

    }


}