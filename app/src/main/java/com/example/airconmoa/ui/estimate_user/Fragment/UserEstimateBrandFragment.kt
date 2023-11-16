package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateBrandBinding
import com.example.airconmoa.ui.estimate_user.EstimateFinishActivity
import com.example.airconmoa.ui.main_user.MainActivity

class UserEstimateBrandFragment: BaseFragmentVB<FragmentUserEstimateBrandBinding>(
    FragmentUserEstimateBrandBinding::bind, R.layout.fragment_user_estimate_brand)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnNext.setOnClickListener {
                val intent = Intent(context, EstimateFinishActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                while (findNavController().popBackStack());
            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                while (findNavController().popBackStack());
            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimateBrandFragment_to_estimateDateFragment)

            }
        }
    }
}