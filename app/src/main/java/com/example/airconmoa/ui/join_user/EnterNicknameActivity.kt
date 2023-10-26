package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterNicknameNextBtn.setOnClickListener {
            if(binding.enterNicknameEt.text.toString().isEmpty()) {
                Toast.makeText(this, "닉네임을 올바로 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, FinishJoinActivity::class.java)
                intent.putExtra("nickname", binding.enterNicknameEt.text.toString())
                startActivity(intent)
            }
        }


    }
}