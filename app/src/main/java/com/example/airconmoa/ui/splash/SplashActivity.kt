package com.example.airconmoa.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.user_or_company.SelectActivity
import com.example.airconmoa_android.databinding.ActivitySplashBinding

class SplashActivity : BaseActivityVB<ActivitySplashBinding>(ActivitySplashBinding::inflate){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        startActivity(Intent(this, SelectActivity::class.java))
        finish()

    }




}