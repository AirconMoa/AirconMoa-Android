package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.R
import com.example.airconmoa.databinding.ActivityEnterPasswordAgainBinding

class EnterPasswordAgainActivity : BaseActivityVB<ActivityEnterPasswordAgainBinding>(
    ActivityEnterPasswordAgainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterPasswordAgainBackIv.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

        binding.enterPasswordAgainFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterPasswordAgainNextBtn.setOnClickListener {
            val intent = Intent(this, EnterPhoneActivity::class.java)
            val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
            val password = sharedPreferences.getString("userPassword", null)

            if (password.equals(binding.enterPasswordAgainEt.text.toString())) {
                Log.d("userPasswordAgain", password.toString())
                val editor = sharedPreferences.edit()
                editor.putString("userPasswordAgain", password.toString())
                editor.apply()
                binding.enterPasswordAgainErrorTv.visibility = View.INVISIBLE
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            }
            else {
                binding.enterPasswordAgainErrorTv.visibility = View.VISIBLE
                showCustomToast("비밀번호가 일치하지 않습니다")
            }
        }
        setFullScreen()
    }
    override fun onPause() {
        super.onPause()
        val userPassword = binding.enterPasswordAgainEt.text.toString()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)

        if(userPassword.isNotEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString("userPasswordAgain", userPassword)
            Log.d("userPasswordAgain", userPassword)
            editor.apply()
        }
    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val tempPassword = sharedPreferences.getString("userPasswordAgain", null)

        if(tempPassword != null) {
            binding.enterPasswordAgainEt.setText(tempPassword)
        }
    }
}