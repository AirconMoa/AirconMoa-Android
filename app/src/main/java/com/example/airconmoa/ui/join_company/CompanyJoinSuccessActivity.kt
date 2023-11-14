package com.example.airconmoa.ui.join_company

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa_android.databinding.ActivityCompanyJoinBinding
import com.example.airconmoa_android.databinding.ActivityCompanyJoinSuccessBinding

class CompanyJoinSuccessActivity: BaseActivityVB<ActivityCompanyJoinSuccessBinding>(
    ActivityCompanyJoinSuccessBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            btnHome.setOnClickListener {
                val intent = Intent(this@CompanyJoinSuccessActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}