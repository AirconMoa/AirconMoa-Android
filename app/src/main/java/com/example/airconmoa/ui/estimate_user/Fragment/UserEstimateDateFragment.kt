package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateDateBinding
import com.example.airconmoa.ui.main_user.MainActivity


class UserEstimateDateFragment : BaseFragmentVB<FragmentUserEstimateDateBinding>(
    FragmentUserEstimateDateBinding::bind, R.layout.fragment_user_estimate_date)   {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.action_estimateDateFragment_to_estimateBrandFragment)

            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                while (findNavController().popBackStack());
            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimateDateFragment_to_estimateQuantityFragment)

            }
        }
    }
}