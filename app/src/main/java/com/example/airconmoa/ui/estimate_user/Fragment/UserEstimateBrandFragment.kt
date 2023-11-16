package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateBrandBinding
import com.example.airconmoa.ui.estimate_user.EstimateFinishActivity
import com.example.airconmoa.ui.main_user.MainActivity

class UserEstimateBrandFragment : BaseFragmentVB<FragmentUserEstimateBrandBinding>(
    FragmentUserEstimateBrandBinding::bind, R.layout.fragment_user_estimate_brand
) {

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        with(binding) {
            btnNext.setOnClickListener { nextActivity(EstimateFinishActivity::class.java) }
            closeBtn.setOnClickListener { nextActivity(MainActivity::class.java) }
            backBtn.setOnClickListener {
                navController.navigate(R.id.action_estimateBrandFragment_to_estimateDateFragment)
            }
            if(lg.isSelected){
                lg.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_true) }
            }
            else{
                lg.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_false2) }
            }
        }
    }

    private fun nextActivity(java: Class<*>) {
        val intent = Intent(context, java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TASK }
        startActivity(intent)
        requireActivity().finish()
    }
}