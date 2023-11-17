package com.example.airconmoa.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.databinding.ActivitySplashBinding
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.ui.login_user.LoginActivity
import com.example.airconmoa.ui.main_company.MainCompanyActivity
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.util.Constants
import com.example.airconmoa.util.FirebaseAuthUtils
import com.kakao.sdk.common.util.Utility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivityVB<ActivitySplashBinding>(ActivitySplashBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)

        val uid = FirebaseAuthUtils.getUid()
        Log.d("userUid", uid)

        val keyHash = Utility.getKeyHash(this)
        //val keyHash="mye820+jetkYmbUWKHMB3uzRVak="
        Log.d("Hash", keyHash)

        val sharedPreferences = getSharedPreferences("airconmoa", MODE_PRIVATE)
        val accessToken = sharedPreferences.getString(Constants.X_ACCESS_TOKEN, null)
        val loginType = sharedPreferences.getString(Constants.X_LOGIN_TYPE, "")

        if(accessToken == null) {
            Log.d("accessToken ", "null")
            startActivity(Intent(this, JoinActivity::class.java))
            finish()
        }
        else {
            Log.d("accessToken ", accessToken)
            Log.d("loginType ", loginType.toString())
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = isTokenExpired(accessToken)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccess) {
                            Log.d("isExpired", response.result.toString())
                            if (response.result!!) { // 토큰 만료
                                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(this@SplashActivity, "인증이 만료되었습니다. 로그인 후 이용해주세요.", Toast.LENGTH_SHORT).show()
                                }
                                finish()
                            } else { // 토큰 유효
                                if (loginType.equals("user"))
                                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                else {
                                    startActivity(Intent(this@SplashActivity, MainCompanyActivity::class.java))
                                }
                                finish()
                            }
                        }
                        else {
                            val message = response.message
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@SplashActivity, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Error", "Exception: ${e.message}")
                }
            }

        }
    }

    private suspend fun isTokenExpired(accessToken: String) : BaseResponse<Boolean> {
        return RetrofitInstance.splashRetrofitInterface.isTokenExpired(accessToken)
    }
}