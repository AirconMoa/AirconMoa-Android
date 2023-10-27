package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterPhoneBinding

class EnterPhoneActivity : BaseActivityVB<ActivityEnterPhoneBinding>(ActivityEnterPhoneBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterPhoneBackIv.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
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
            val phoneNumber = "$baseSeq-$firstSeq-$lastSeq"
            Log.d("phone-number", phoneNumber)

            if (isValidPhoneNumber(baseSeq, firstSeq, lastSeq)) {
                val intent = Intent(this, EnterNicknameActivity::class.java)
                val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("userPhoneNumber", phoneNumber)
                editor.apply()
                binding.enterPhoneErrorTv.visibility = View.INVISIBLE
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            } else {
                binding.enterPhoneErrorTv.visibility = View.VISIBLE
                showCustomToast("전화번호를 올바르게 입력해주세요")
            }
        }

        setFullScreen()
    }

    override fun onPause() {
        super.onPause()
        val baseSeq = binding.enterPhoneBaseEt.text.toString() // 전화번호의 제일 처음 부분
        val firstSeq = binding.enterPhoneFisrtEt.text.toString()
        val lastSeq = binding.enterPhoneLastEt.text.toString()

        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if(baseSeq.isNotEmpty()) {
            editor.putString("baseSeq", baseSeq)
            Log.d("baseSeq", baseSeq)
        }

        if(firstSeq.isNotEmpty()) {
            editor.putString("firstSeq", firstSeq)
            Log.d("firstSeq", firstSeq)
        }

        if(lastSeq.isNotEmpty()) {
            editor.putString("lastSeq", lastSeq)
            Log.d("lastSeq", lastSeq)
        }

        editor.apply()
    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val tempBase = sharedPreferences.getString("baseSeq", null)
        val tempFirst = sharedPreferences.getString("firstSeq", null)
        val tempLast = sharedPreferences.getString("lastSeq", null)

        if(tempBase != null) {
            binding.enterPhoneBaseEt.setText(tempBase)
        }

        if(tempFirst != null) {
            binding.enterPhoneFisrtEt.setText(tempFirst)
        }

        if(tempLast != null) {
            binding.enterPhoneLastEt.setText(tempLast)
        }
    }

    private fun isValidPhoneNumber(baseSeq: String, firstSeq: String, lastSeq: String): Boolean {
        return baseSeq.length == 3 && firstSeq.length == 4 && lastSeq.length == 4 &&
                baseSeq.all { it.isDigit() } && firstSeq.all { it.isDigit() } && lastSeq.all { it.isDigit() }
    }
}