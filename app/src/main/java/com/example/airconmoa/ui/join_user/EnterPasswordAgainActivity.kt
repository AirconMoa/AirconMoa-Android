package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterPasswordAgainBinding
import com.example.airconmoa_android.databinding.ActivityEnterPasswordBinding

class EnterPasswordAgainActivity : BaseActivityVB<ActivityEnterPasswordAgainBinding>(
    ActivityEnterPasswordAgainBinding::inflate) {

    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("password")) {
            password = intent.getStringExtra("password")!!
        }

        binding.enterPasswordAgainBackIv.setOnClickListener {
            finish()
        }

        binding.enterPasswordAgainFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterPasswordAgainNextBtn.setOnClickListener {
            val intent = Intent(this, EnterPhoneActivity::class.java)
            if(password.equals(binding.enterPasswordAgainEt.text.toString())) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}