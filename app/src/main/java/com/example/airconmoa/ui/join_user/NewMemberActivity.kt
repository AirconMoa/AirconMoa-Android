package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.login_user.LoginActivity
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityJoinBinding
import com.example.airconmoa_android.databinding.ActivityNewMemberBinding

class NewMemberActivity : BaseActivityVB<ActivityNewMemberBinding>(ActivityNewMemberBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.newlyJoinBtn.setOnClickListener {
            binding.newlyJoinBtn.visibility = View.INVISIBLE
            binding.newlyJoinSelectedBtn.visibility = View.VISIBLE

            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        binding.alreadyJoinBtn.setOnClickListener {
            binding.alreadyJoinBtn.visibility = View.INVISIBLE
            binding.alreadyJoinSelectedBtn.visibility = View.VISIBLE

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        setFullScreen()
    }

    override fun onResume() {
        super.onResume()

        binding.newlyJoinBtn.visibility = View.VISIBLE
        binding.newlyJoinSelectedBtn.visibility = View.INVISIBLE
        binding.alreadyJoinBtn.visibility = View.VISIBLE
        binding.alreadyJoinSelectedBtn.visibility = View.INVISIBLE
    }
}