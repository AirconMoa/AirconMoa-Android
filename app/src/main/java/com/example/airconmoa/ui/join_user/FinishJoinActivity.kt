package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterPhoneBinding
import com.example.airconmoa_android.databinding.ActivityFinishJoinBinding

class FinishJoinActivity : BaseActivityVB<ActivityFinishJoinBinding>(ActivityFinishJoinBinding::inflate) {

    lateinit var nickname : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("nickname")) {
            nickname = intent.getStringExtra("nickname")!!
        }

        binding.finishJoinUserName.text = nickname + "님,"

        binding.finishJoinNextBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        setFullScreen()
    }
}