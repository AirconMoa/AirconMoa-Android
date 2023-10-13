package com.example.airconmoa.config

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
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
// Intercepter를 구현한 클래스는 HTTP 요청을 가로채고 처리한다. 주로, 사용자 인증 및 토큰 관리와 관련된 작업을 수행한다.
// AccessTokenInterceptor는 Intercepter의 구체 클래스이자, RefreshTokenInterface 인터페이스를 상속받는다.

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(IOException::class) // IOException이 발생할 수 있음
    // intercept 메서드는 HTTP 요청을 가로채고 처리하는 로직을 포함한다.
    override fun intercept(chain: Interceptor.Chain): Response {
        // jwt에 X_ACCESS_TOKEN이라는 이름으로 저장된 문자열 값을 넣는다.
        val jwt = sharedPreferences.getString(X_ACCESS_TOKEN,"")?:""
        // refreshToken에 X_REFRESH_TOKEN이라는 이름으로 저장된 문자열 값을 넣는다.
        val refreshToken = sharedPreferences.getString(X_REFRESH_TOKEN,"")?:""
        // 만료일자를 나타내는 문자열을 넣는다.
        val accessExpire = sharedPreferences.getString(X_ACCESS_EXPIRE,"")?:""
        val refreshExpire = sharedPreferences.getString(X_REFRESH_EXPIRE,"")?:""

        if (jwt.isNotBlank()) { // 액세스 토큰이 존재하면
            if(isDatePassed(accessExpire)){ // 액세스 토큰이 만료된 경우
                if(isDatePassed(refreshExpire)){ // 리프레시 토큰도 만료된 경우
                    sessionExpired() // 세션 만료를 알리고, LoginActivity로 전환
                } else { // 리프레시 토큰은 만료되지 않은 경우
                    // 리프레시 토큰을 이용해서 액세스 토큰을 재발급 받는 로직
                    RefreshTokenService(this).refreshJwt(RefreshJwtPostData(refreshToken))
                }

            } else // 액세스 토큰이 유효한 경우
                return initHeader(chain) // HTTP 요청 헤더를 초기화
        }

        return initHeader(chain)
    }

    private fun initHeader(chain : Interceptor.Chain): Response{
    // chain은 현재의 HTTP 요청을 나타낸다.
        val jwt: String? = sharedPreferences.getString(X_ACCESS_TOKEN, null)
        // 요청에 대한 request 객체를 가져온 후 새로운 요청을 만들어 builder에 할당한다.
        // 현재 요청 자체를 수정하면 자칫 문제가 발생할 수 있기 때문에 현재 요청을 Clone한
        // Request.Builder를 만들어 안전하게 요청을 수정하고 변경한다.
        val builder: Request.Builder = chain.request().newBuilder()
        if(jwt != null){ // 액세스 토큰이 존재하면
            builder.addHeader("Bearer-Token", jwt)
        }
        builder.addHeader("x-api-key", xapikey)
        // 새롭게 생성된 HTTP 요청을 서버로 보낸다.
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
    @RequiresApi(Build.VERSION_CODES.O)
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
        sharedPreferences.edit() // SharedPreferences 객체의 편집기를 얻는다.
            // key-value 쌍의 데이터를 저장한다.
            .putString(X_ACCESS_TOKEN, "Bearer " + result.accessToken)
            .putString(X_REFRESH_TOKEN, result.refreshToken)
            .putString(X_ACCESS_EXPIRE, result.accessTokenExpiredDate)
            .putString(X_REFRESH_EXPIRE, result.refreshTokenExpiredDate)
            .apply() // SharedPreferences에 대한 변경 내용을 적용하고 저장한다.
    }
}