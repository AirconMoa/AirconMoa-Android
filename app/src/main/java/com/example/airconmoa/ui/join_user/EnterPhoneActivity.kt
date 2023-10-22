package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterPasswordBinding
import com.example.airconmoa_android.databinding.ActivityEnterPhoneBinding

class EnterPhoneActivity : BaseActivityVB<ActivityEnterPhoneBinding>(ActivityEnterPhoneBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterPhoneBackIv.setOnClickListener {
            finish()
        }

        binding.enterPhoneFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}