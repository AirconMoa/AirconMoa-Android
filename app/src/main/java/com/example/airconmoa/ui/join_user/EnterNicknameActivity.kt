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
import com.example.airconmoa_android.databinding.ActivityEnterNicknameBinding

class EnterNicknameActivity : BaseActivityVB<ActivityEnterNicknameBinding>(ActivityEnterNicknameBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterNicknameBackIv.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

        binding.enterNicknameFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterNicknameNextBtn.setOnClickListener {
            val nickname = binding.enterNicknameEt.text.toString()
            if(nickname.isEmpty() || nickname.length > 10) {
                binding.enterNicknameErrorTv.visibility = View.VISIBLE
                showCustomToast("닉네임은 1~10글자 이내로 입력해주세요")
            }
            else {
                val intent = Intent(this, FinishJoinActivity::class.java)
                intent.putExtra("nickname", binding.enterNicknameEt.text.toString())
                // 유저의 정보
                val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                val userEmail = sharedPreferences.getString("userEmail", null)
                val userPassword = sharedPreferences.getString("userPassword", null)
                val userPhoneNumber = sharedPreferences.getString("userPhoneNumber", null)
                Log.d("userInfo", userEmail!! + "/" +userPassword!! + "/"
                        + userPhoneNumber!! + "/" + binding.enterNicknameEt.text.toString())

                // sharedPreferences내에 저장된 유저의 모든 데이터를 삭제함
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                binding.enterNicknameErrorTv.visibility = View.INVISIBLE
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            }
        }
        setFullScreen()
    }

    override fun onPause() {
        super.onPause()
        val userNickname = binding.enterNicknameEt.text.toString()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)

        if(userNickname.isNotEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString("userNickname", userNickname)
            Log.d("userNickname", userNickname)
            editor.apply()
        }
    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val tempNickname = sharedPreferences.getString("userNickname", null)

        if(tempNickname != null) {
            binding.enterNicknameEt.setText(tempNickname)
        }
    }

}