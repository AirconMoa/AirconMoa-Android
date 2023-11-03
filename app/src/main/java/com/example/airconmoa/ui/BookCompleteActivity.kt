package com.example.airconmoa.ui

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa_android.databinding.ActivityBookCompleteBinding

class BookCompleteActivity : BaseActivityVB<ActivityBookCompleteBinding>(ActivityBookCompleteBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.homeBtn.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}