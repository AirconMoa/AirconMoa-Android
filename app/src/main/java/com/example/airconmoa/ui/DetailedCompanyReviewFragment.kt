package com.example.airconmoa.ui

import android.os.Bundle
import android.view.View
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.FragmentDetailedCompanyReviewBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailedCompanyReviewFragment : BaseFragmentVB<FragmentDetailedCompanyReviewBinding>(FragmentDetailedCompanyReviewBinding::bind, R.layout.fragment_detailed_company_review) {
    private val tabTitle= arrayListOf("상세정보","리뷰")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estimateDetialVPAdapter=EstimateDatailVPAdapter(this@DetailedCompanyReviewFragment)
        binding.vpDetailedCompany.adapter=estimateDetialVPAdapter

        TabLayoutMediator(binding.tabDetailedCompany,binding.vpDetailedCompany){
                tab,position->
            tab.text=tabTitle[position]
        }.attach()
    }

}