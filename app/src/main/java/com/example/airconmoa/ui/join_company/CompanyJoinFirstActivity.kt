package com.example.airconmoa.ui.join_company

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.databinding.ActivityCompanyJoinBinding
import com.example.airconmoa_android.databinding.ActivityCompanyJoinFirstBinding

class CompanyJoinFirstActivity: BaseActivityVB<ActivityCompanyJoinFirstBinding>(
    ActivityCompanyJoinFirstBinding::inflate)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            btnYes.setOnClickListener {
                val intent = Intent(this@CompanyJoinFirstActivity,CompanyJoinActivity::class.java)
                startActivity(intent)
            }
        }


    }
}