package com.example.airconmoa.config

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.airconmoa.util.Constants.BASE_URL
import com.kakao.sdk.common.KakaoSdk

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {
// App 클래스가 상속받은 Application 클래스는 앱의 생명주기를 관리하고
// 앱 전체에서 공유할 수 있는 전역 상태를 관리하는 목적으로 사용된다

    //  앱의 context 를 instance 변수에 저장
    init {
        instance = this // App 클래스의 인스턴스를 다른 곳에서 사용할 수 있게 한다.
    }

    companion object{ // 애플리케이션 레벨에서 공유할 여러 개의 정적 변수와 함수를 선언한다.
        lateinit var instance : App // App 클래스의 인스턴스를 참조하는 변수이다.
        lateinit var sharedPreferences: SharedPreferences // 앱의 설정 및 데이터를 저장하기 위해 사용된다.
        lateinit var retrofit : Retrofit // Retrofit 라이브러리를 사용하여 네트워크 통신을 관리하기 위한 변수이다.

        // 앱의 context 를 반환하는 함수
        fun context() : Context {
            return instance.applicationContext
        }

        // Retrofit 인스턴스를 반환하는 함수
        fun getRetro() : Retrofit{
            return retrofit.newBuilder()
                .baseUrl(BASE_URL) // BaseUrl은 Constants에서 가져온 값이다.
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        // "CHAMMA_APP"이라는 이름의 SharedPreferences 인스턴스를 생성하고, 해당 인스턴스를 sharedPreferences 변수에 저장한다.
        // 이후 앱에서 SharedPreferences를 사용하여 데이터를 읽거나 쓸 수 있게 된다.
        sharedPreferences =
            applicationContext.getSharedPreferences("MOA_APP", MODE_PRIVATE)
        initRetrofitInstance() // Retrofit을 초기화한다.
        //getkakaoKeyhash()
        //startSocialLogin()

        KakaoSdk.init(this, "64eccc279aa42b2d6d95605fcb8b3387")
    }

    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder() // OkHttpClient는 HTTP 요청 및 응답을 관리하는 클라이언트이다.
            .readTimeout(30000, TimeUnit.MILLISECONDS) // read 타임아웃을 30초로 설정한다.
            .connectTimeout(30000, TimeUnit.MILLISECONDS) // connection 타임아웃을 30초로 설정한다.

            // HttpLoggingInterceptor를 추가하여 HTTP 요청과 응답에 로깅을 수행한다. 로깅 수준은 Level.BODY는 모든 HTTP 데이터를 로그에 표시한다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

            // AccessTokenInterceptor를 추가하여 JWT 토큰을 자동으로 HTTP 요청 헤더에 추가한다.
            // applicationContext는 Android 애플리케이션 컨텍스트를 나타낸다.
            //.addNetworkInterceptor(AccessTokenInterceptor(applicationContext))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // OkHttpClient
            .addConverterFactory(GsonConverterFactory.create()) // Gson(JSON 파싱 라이브러리)을 이용해 JSON 데이터를 객체로 변환한다.
            .build()
    }
/*

    private fun startSocialLogin() {
        KakaoSdk.init(this, kakaoAppKey)
        NaverIdLoginSDK.initialize(this,
            naverClientId,
            naverClientSecret,
            naverClientName
        )
    }
*/
}