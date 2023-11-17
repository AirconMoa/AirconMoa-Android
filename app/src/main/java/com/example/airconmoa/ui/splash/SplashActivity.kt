package com.example.airconmoa.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivitySplashBinding
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.ui.main_company.MainCompanyActivity
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.util.Constants
import com.example.airconmoa.util.FirebaseAuthUtils
import com.kakao.sdk.common.util.Utility

class SplashActivity : BaseActivityVB<ActivitySplashBinding>(ActivitySplashBinding::inflate){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)

        val uid = FirebaseAuthUtils.getUid()
        Log.d("userUid", uid)

        val keyHash = Utility.getKeyHash(this)
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
            if (loginType.equals("user"))
                startActivity(Intent(this, MainActivity::class.java))
            else {
                startActivity(Intent(this, MainCompanyActivity::class.java))
            }
            // startActivity(Intent(this, JoinActivity::class.java))
            finish()
        }
    }
}