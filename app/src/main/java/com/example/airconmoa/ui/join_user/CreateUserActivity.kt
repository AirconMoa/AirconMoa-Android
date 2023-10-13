package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityCreateUserBinding
import com.example.airconmoa_android.databinding.ActivityJoinBinding

class CreateUserActivity : BaseActivityVB<ActivityCreateUserBinding>(ActivityCreateUserBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.createUserBackIv.setOnClickListener {
            finish()
        }

        binding.createUserFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }
    }
}