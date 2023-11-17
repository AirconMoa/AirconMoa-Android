package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateQuantityBinding
import com.example.airconmoa.ui.main_user.MainActivity


class UserEstimateQuantityFragment : BaseFragmentVB<FragmentUserEstimateQuantityBinding>(
    FragmentUserEstimateQuantityBinding::bind, R.layout.fragment_user_estimate_quantity
) {

    private var quantity=1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var info1=arguments?.getString("info1").toString()
        var info2=arguments?.getString("info2").toString()
        var info3=arguments?.getString("info3").toString()
        Log.d("info1", "Q "+info1)
        Log.d("info1", "Q "+info2)
        Log.d("info1", "Q "+info3)


        with(binding) {
            btnNext.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("info1", info1)
                bundle.putString("info2",info2)
                bundle.putString("info3",info3)
                bundle.putInt("info4",quantity)
                findNavController().navigate(R.id.action_estimateQuantityFragment_to_estimateDateFragment,bundle)

            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
                while (findNavController().popBackStack());

            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimateQuantityFragment_to_estimateTypeFragment)

            }
            minusBtn.setOnClickListener {
                if(quantity>0) {
                    numTv.text = (quantity - 1).toString()
                    quantity -= 1
                }
            }
            plusBtn.setOnClickListener {
                numTv.text=(quantity+1).toString()
                quantity += 1

            }
        }
    }
}