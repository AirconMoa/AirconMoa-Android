package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateBinding
import com.example.airconmoa.ui.main_user.MainActivity

class UserEstimateFragment : BaseFragmentVB<FragmentUserEstimateBinding>(FragmentUserEstimateBinding::bind, R.layout.fragment_user_estimate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.action_estimateUserFragment_to_estimateAddressFragment)

            }
            closeBtn.setOnClickListener {
                while(findNavController().popBackStack());
                val intent= Intent(context,MainActivity::class.java)
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            backBtn.setOnClickListener {
                while(findNavController().popBackStack());

                val intent= Intent(context,MainActivity::class.java)
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
            }

            if(imgNew.isSelected){
                imgNew.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_true) }
            }
            else{
                imgNew.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_false2) }
            }
            if(imgChange.isSelected){
                imgNew.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_true) }
            }
            else{
                imgNew.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_false2) }
            }
            if(imgExist.isSelected){
                imgNew.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_true) }
            }
            else{
                imgNew.background =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.estimate_btn_check_false2) }
            }
        }


    }

}