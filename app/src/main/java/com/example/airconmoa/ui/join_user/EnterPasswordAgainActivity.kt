package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterPasswordAgainBinding
import com.example.airconmoa_android.databinding.ActivityEnterPasswordBinding

class EnterPasswordAgainActivity : BaseActivityVB<ActivityEnterPasswordAgainBinding>(
    ActivityEnterPasswordAgainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterPasswordAgainBackIv.setOnClickListener {
            finish()
        }

        binding.enterPasswordAgainFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }
    }
}