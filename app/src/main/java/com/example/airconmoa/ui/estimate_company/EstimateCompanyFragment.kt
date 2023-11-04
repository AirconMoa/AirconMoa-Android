package com.example.airconmoa.ui.estimate_company

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.FragmentEstimateCompanyBinding

class EstimateCompanyFragment : BaseFragmentVB<FragmentEstimateCompanyBinding>(FragmentEstimateCompanyBinding::bind, R.layout.fragment_estimate_company) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList:ArrayList<EstimateItemData> = arrayListOf()
        dataList.apply {
            add(
                EstimateItemData("모아", "2023.09.26", "신규설치 · 신규건물", "500m")

            )
            add(
                EstimateItemData("모아2", "2023.09.27", "신규설치 · 기존건물", "510m")
            )
            add(
                EstimateItemData("모아3", "2023.10.01", "신규설치 · 기존건물", "450m")
            )
            add(
                EstimateItemData("모아4", "2023.10.03", "교체", "650m")
            )
            add(
                EstimateItemData("모아5", "2023.10.04", "신규설치 · 기존건물", "650m")
            )
        }

        binding.rvMainCompany.adapter= EstimateItemDataAdapter(dataList)
        binding.rvMainCompany.layoutManager= LinearLayoutManager(context)
    }
}