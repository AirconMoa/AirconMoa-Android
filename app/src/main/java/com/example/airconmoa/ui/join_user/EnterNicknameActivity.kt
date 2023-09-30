package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterEmailBinding
import com.example.airconmoa_android.databinding.ActivityEnterNicknameBinding

class EnterNicknameActivity : BaseActivityVB<ActivityEnterNicknameBinding>(ActivityEnterNicknameBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterNicknameBackIv.setOnClickListener {
            finish()
        }

        binding.enterNicknameFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }
    }
}