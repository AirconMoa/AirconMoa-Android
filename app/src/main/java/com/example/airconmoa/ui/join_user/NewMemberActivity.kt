package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.login_user.LoginActivity
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityJoinBinding
import com.example.airconmoa_android.databinding.ActivityNewMemberBinding

class NewMemberActivity : BaseActivityVB<ActivityNewMemberBinding>(ActivityNewMemberBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.newlyJoinBtn.setOnClickListener {
            binding.newlyJoinBtn.visibility = View.GONE
            binding.newlyJoinSelectedBtn.visibility = View.VISIBLE

            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }

        binding.alreadyJoinBtn.setOnClickListener {
            binding.alreadyJoinBtn.visibility = View.GONE
            binding.alreadyJoinSelectedBtn.visibility = View.VISIBLE

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}