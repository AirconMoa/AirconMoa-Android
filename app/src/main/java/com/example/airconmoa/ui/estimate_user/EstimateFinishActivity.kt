package com.example.airconmoa.ui.estimate_user

import android.os.Bundle
import android.util.Log
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityEstimateFinishBinding

class EstimateFinishActivity :
    BaseActivityVB<ActivityEstimateFinishBinding>(ActivityEstimateFinishBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            Log.d("info1 ",intent!!.getStringExtra("info1").toString())
            installContentTv.text = intent!!.getStringExtra("info1").toString()
            installAddressContentTv.text = intent!!.getStringExtra("info2").toString()
            installSiteContentTv.text = intent!!.getStringExtra("info3").toString()
            installNumContentTv.text = intent!!.getStringExtra("info4").toString()
            installDateContentTv.text = intent!!.getStringExtra("info5").toString()
            brandContentTv.text = intent!!.getStringExtra("info6").toString()

            backBtn.setOnClickListener {
                finish()
            }
            installContentTv.text
        }
    }
}