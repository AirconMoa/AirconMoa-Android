package com.example.airconmoa.ui.estimate_company

import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityEditCompleteBinding

class EditCompleteActivity :
    BaseActivityVB<ActivityEditCompleteBinding>(ActivityEditCompleteBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}