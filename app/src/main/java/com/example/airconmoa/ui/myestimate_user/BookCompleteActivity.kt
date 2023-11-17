package com.example.airconmoa.ui.myestimate_user

import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityBookCompleteBinding

class BookCompleteActivity :
    BaseActivityVB<ActivityBookCompleteBinding>(ActivityBookCompleteBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.homeBtn.setOnClickListener {
            finish()
        }
    }
}