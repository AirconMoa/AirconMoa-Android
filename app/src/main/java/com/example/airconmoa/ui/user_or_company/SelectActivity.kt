package com.example.airconmoa.ui.user_or_company

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.join_user.NewMemberActivity
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.databinding.ActivitySelectBinding

class SelectActivity : BaseActivityVB<ActivitySelectBinding>(ActivitySelectBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.companyBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))

        }
        binding.userBtn.setOnClickListener {
            startActivity(Intent(this, NewMemberActivity::class.java))

        }

    }
}