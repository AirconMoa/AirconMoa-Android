package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
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
            finish()
        }

        binding.enterPhoneNextBtn.setOnClickListener {
            val baseSeq = binding.enterPhoneBaseEt.text.toString()
            val firstSeq = binding.enterPhoneFisrtEt.text.toString()
            val lastSeq = binding.enterPhoneLastEt.text.toString()
            Log.d("phone-number", "$baseSeq-$firstSeq-$lastSeq")
            if(baseSeq.length == 3 && firstSeq.length == 4 && lastSeq.length == 4) {
                val intent = Intent(this, EnterNicknameActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "전화번호를 올바로 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}