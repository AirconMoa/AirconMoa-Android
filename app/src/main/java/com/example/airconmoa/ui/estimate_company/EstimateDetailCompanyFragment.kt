package com.example.airconmoa.ui.estimate_company

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentEstimateDetailCompanyBinding


class EstimateDetailCompanyFragment : BaseFragmentVB<FragmentEstimateDetailCompanyBinding>(
    FragmentEstimateDetailCompanyBinding::bind,
    R.layout.fragment_estimate_detail_company
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.writeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_estimateDetailCompanyFragment_to_estimateWriteCompanyFragment)

        }
    }
}