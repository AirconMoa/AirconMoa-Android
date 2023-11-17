package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityJoinBinding
import com.example.airconmoa.ui.join_company.CompanyJoinActivity
import com.example.airconmoa.ui.main_user.MainActivity
import com.kakao.sdk.common.util.Utility

class JoinActivity : BaseActivityVB<ActivityJoinBinding>(ActivityJoinBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.joinUserBtn.setOnClickListener {
            binding.joinUserBtn.visibility = View.INVISIBLE
            binding.joinUserSelectedBtn.visibility = View.VISIBLE

            //val intent = Intent(this, NewMemberActivity::class.java)
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        binding.joinCompanyBtn.setOnClickListener {
            binding.joinCompanyBtn.visibility = View.INVISIBLE
            binding.joinCompanySelectedBtn.visibility = View.VISIBLE

            val intent = Intent(this, CompanyJoinActivity::class.java)
            //val intent = Intent(this, MainCompanyActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        setFullScreen()
    }

    override fun onResume() {
        super.onResume()

        binding.joinUserBtn.visibility = View.VISIBLE
        binding.joinUserSelectedBtn.visibility = View.INVISIBLE
        binding.joinCompanyBtn.visibility = View.VISIBLE
        binding.joinCompanySelectedBtn.visibility = View.INVISIBLE
    }
}