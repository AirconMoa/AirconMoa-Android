package com.example.airconmoa.ui.join_company

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityCompanyJoinFirstBinding
import com.example.airconmoa.ui.login_company.LoginCompanyActivity


class CompanyJoinFirstActivity: BaseActivityVB<ActivityCompanyJoinFirstBinding>(
    ActivityCompanyJoinFirstBinding::inflate)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            btnGoJoin.setTextColor(Color.parseColor("#343A40"))
            btnGoJoin.background = ContextCompat.getDrawable(this@CompanyJoinFirstActivity, R.drawable.company_next_btn_false)

            btnGoLogin.setTextColor(Color.parseColor("#343A40"))
            btnGoLogin.background = ContextCompat.getDrawable(this@CompanyJoinFirstActivity, R.drawable.company_next_btn_false)

            btnGoJoin.setOnClickListener {
                btnGoJoin.setTextColor(Color.parseColor("#FFFFFF"))
                btnGoJoin.background = ContextCompat.getDrawable(this@CompanyJoinFirstActivity, R.drawable.company_next_btn_true)
                val intent = Intent(this@CompanyJoinFirstActivity,CompanyJoinActivity::class.java)
                startActivity(intent)
            }

            btnGoLogin.setOnClickListener {
                btnGoLogin.setTextColor(Color.parseColor("#FFFFFF"))
                btnGoLogin.background = ContextCompat.getDrawable(this@CompanyJoinFirstActivity, R.drawable.company_next_btn_true)
                val intent = Intent(this@CompanyJoinFirstActivity,LoginCompanyActivity::class.java)
                startActivity(intent)
            }
        }


    }
}