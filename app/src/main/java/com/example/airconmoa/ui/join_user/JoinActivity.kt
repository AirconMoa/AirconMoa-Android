package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityJoinBinding
import com.example.airconmoa_android.databinding.ActivityLoginCompanyBinding
import com.kakao.sdk.common.util.Utility

class JoinActivity : BaseActivityVB<ActivityJoinBinding>(ActivityJoinBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.joinUserBtn.setOnClickListener {
            binding.joinUserBtn.visibility = View.GONE
            binding.joinUserSelectedBtn.visibility = View.VISIBLE

            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }

        binding.joinCompanyBtn.setOnClickListener {
            binding.joinCompanyBtn.visibility = View.GONE
            binding.joinCompanySelectedBtn.visibility = View.VISIBLE
        }

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)
    }
}