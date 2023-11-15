package com.example.airconmoa.ui.estimate_user

import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityEstimateFinishBinding

class EstimateFinishActivity : BaseActivityVB<ActivityEstimateFinishBinding>(ActivityEstimateFinishBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}