package com.example.airconmoa.ui.estimate_company

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentEstimateEditCompanyBinding


class EstimateEditCompanyFragment : BaseFragmentVB<FragmentEstimateEditCompanyBinding>(
    FragmentEstimateEditCompanyBinding::bind,
    R.layout.fragment_estimate_edit_company
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.finishBtn.setOnClickListener {
            val intent = Intent(context, EditCompleteActivity::class.java)
            startActivity(intent)
            while (findNavController().popBackStack());
        }
    }
}