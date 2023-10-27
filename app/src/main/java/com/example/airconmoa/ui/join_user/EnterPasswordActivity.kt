package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterEmailBinding
import com.example.airconmoa_android.databinding.ActivityEnterPasswordBinding

class EnterPasswordActivity : BaseActivityVB<ActivityEnterPasswordBinding>(ActivityEnterPasswordBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterPasswordBackIv.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

        binding.enterPasswordFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterPasswordNextBtn.setOnClickListener {
            val password = binding.enterPasswordSelectedEt.text
            if(password!!.length < 6 || password!!.length > 12) {
                binding.enterPasswordErrorTv.visibility = View.VISIBLE
                showCustomToast("비밀번호는 6~12자 이내로 입력해주세요")
            }
            else {
                val intent = Intent(this, EnterPasswordAgainActivity::class.java)
                Log.d("userPassword", password.toString())
                val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("userPassword", password.toString())
                editor.apply()
                binding.enterPasswordErrorTv.visibility = View.INVISIBLE
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            }
        }
        setFullScreen()
    }

    override fun onPause() {
        super.onPause()
        val userPassword = binding.enterPasswordSelectedEt.text.toString()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)

        if(userPassword.isNotEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString("userPassword", userPassword)
            Log.d("userPassword", userPassword)
            editor.apply()
        }
    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val tempPassword = sharedPreferences.getString("userPassword", null)

        if(tempPassword != null) {
            binding.enterPasswordSelectedEt.setText(tempPassword)
        }
    }

}