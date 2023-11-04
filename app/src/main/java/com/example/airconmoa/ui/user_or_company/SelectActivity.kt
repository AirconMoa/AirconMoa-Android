package com.example.airconmoa.ui.user_or_company

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.main_company.MainCompanyActivity
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa_android.databinding.ActivitySelectBinding

class SelectActivity : BaseActivityVB<ActivitySelectBinding>(ActivitySelectBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.companyBtn.setOnClickListener{
            startActivity(Intent(this, MainCompanyActivity::class.java))

        }
        binding.userBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }

    }
}