package com.example.airconmoa.ui.estimate_company

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentEstimateCompanyBinding


class EstimateCompanyFragment : BaseFragmentVB<FragmentEstimateCompanyBinding>(FragmentEstimateCompanyBinding::bind, R.layout.fragment_estimate_company) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList:ArrayList<EstimateItemData> = arrayListOf()
        dataList.apply {
            add(
                EstimateItemData("모아", "2023.09.26", "신규설치 · 신규건물", "500m",R.drawable.detail_review_user_profile)

            )
            add(
                EstimateItemData("모아2", "2023.09.27", "신규설치 · 기존건물", "510m",R.drawable.detail_review_user_profile)
            )
            add(
                EstimateItemData("모아3", "2023.10.01", "신규설치 · 기존건물", "450m",R.drawable.detail_review_user_profile)
            )
            add(
                EstimateItemData("모아4", "2023.10.03", "교체", "650m",R.drawable.detail_review_user_profile)
            )
            add(
                EstimateItemData("모아5", "2023.10.04", "신규설치 · 기존건물", "650m",R.drawable.detail_review_user_profile)
            )
        }

        binding.rvMainCompany.adapter= EstimateItemDataAdapter(dataList,this@EstimateCompanyFragment)
        binding.rvMainCompany.layoutManager= LinearLayoutManager(context)
    }
}