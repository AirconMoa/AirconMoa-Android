package com.example.airconmoa.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentEstimateDetailsBinding


class EstimateDetailsFragment : BaseFragmentVB<FragmentEstimateDetailsBinding>(FragmentEstimateDetailsBinding::bind, R.layout.fragment_estimate_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimatedetailsFragment_to_estimatelistFragment)

            }
            closeBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimatedetailsFragment_to_estimatelistFragment)

            }
            bookBtn.setOnClickListener {
                val intent = Intent(requireContext(), BookCompleteActivity::class.java)
                startActivity(intent)
                while(findNavController().popBackStack());
            }
            companyNameTv.setOnClickListener {
                findNavController().navigate(R.id.action_estimatedetailsFragment_to_detailedcompanyreviewFragment)
            }
        }

    }

}