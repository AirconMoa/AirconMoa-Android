package com.example.airconmoa.ui.join_company

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityCompanyJoinSuccessBinding
import com.example.airconmoa.ui.login_company.LoginCompanyActivity
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.until.getCompanyName

class CompanyJoinSuccessActivity: BaseActivityVB<ActivityCompanyJoinSuccessBinding>(
    ActivityCompanyJoinSuccessBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            txtComName.text = getCompanyName()

            btnNext.setOnClickListener {
                val intent = Intent(this@CompanyJoinSuccessActivity, LoginCompanyActivity::class.java)
                startActivity(intent)
            }

        }
    }
}