package com.example.airconmoa.config

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.airconmoa.config.App.Companion.sharedPreferences
import com.example.airconmoa.until.Constants.X_ACCESS_EXPIRE
import com.example.airconmoa.until.Constants.X_ACCESS_TOKEN
import com.example.airconmoa.until.Constants.X_REFRESH_EXPIRE
import com.example.airconmoa.until.Constants.X_REFRESH_TOKEN
import com.example.airconmoa.until.Constants.xapikey
import com.example.airconmoa.ui.splash.model.RefreshJwtPostData
import com.example.airconmoa.ui.splash.RefreshTokenService
import com.example.airconmoa.ui.splash.RefreshTokenInterface
import com.example.airconmoa.ui.login_user.model.LoginResponseData

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AccessTokenInterceptor(private val context: Context) : Interceptor, RefreshTokenInterface {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val jwt = sharedPreferences.getString(X_ACCESS_TOKEN,"")?:""
        val refreshToken = sharedPreferences.getString(X_REFRESH_TOKEN,"")?:""
        val accessExpire = sharedPreferences.getString(X_ACCESS_EXPIRE,"")?:""
        val refreshExpire = sharedPreferences.getString(X_REFRESH_EXPIRE,"")?:""


        if (jwt.isNotBlank()) {
            if(isDatePassed(accessExpire)){
                if(isDatePassed(refreshExpire)){
                    sessionExpired()
                }else RefreshTokenService(this).refreshJwt(RefreshJwtPostData(refreshToken))

            }else return initHeader(chain)
        }

        return initHeader(chain)
    }

    private fun initHeader(chain : Interceptor.Chain): Response{
        val jwt: String? = sharedPreferences.getString(X_ACCESS_TOKEN, null)
        val builder: Request.Builder = chain.request().newBuilder()
        if(jwt != null){
            builder.addHeader("Bearer-Token", jwt)
        }
        builder.addHeader("x-api-key", xapikey)
        return chain.proceed(builder.build())
    }


    private fun sessionExpired() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable{
            val toast = Toast.makeText(context, "세션이 만료되었습니다", Toast.LENGTH_SHORT)
            toast.show()
        },0)

       // val intent = Intent(context, LoginActivity::class.java)
       // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
       // context.startActivity(intent)
    }

    // 현재날짜가 주어진날짜 보다 지났는지 판별해주는 함수
    private fun isDatePassed(dateStr : String) : Boolean{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val date = LocalDateTime.parse(dateStr, formatter)
        val now = LocalDateTime.now()

        return now.isAfter(date)
    }


    override fun onPostRefreshJwtSuccess(data: LoginResponseData) {
        storeTokens(data)
    }

    override fun onPostRefreshJwtFailure(message: String) {
    }

    private fun storeTokens(result : LoginResponseData){
        sharedPreferences.edit()
            .putString(X_ACCESS_TOKEN, "Bearer " + result.accessToken)
            .putString(X_REFRESH_TOKEN, result.refreshToken)
            .putString(X_ACCESS_EXPIRE, result.accessTokenExpiredDate)
            .putString(X_REFRESH_EXPIRE, result.refreshTokenExpiredDate)
            .apply()
    }

}