package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.databinding.ActivityAgreementBinding
import com.example.airconmoa_android.databinding.ActivityCreateUserBinding

class AgreementActivity : BaseActivityVB<ActivityAgreementBinding>(ActivityAgreementBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.agreementBackIv.setOnClickListener {
            finish()
        }

        binding.agreementFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }
    }
}