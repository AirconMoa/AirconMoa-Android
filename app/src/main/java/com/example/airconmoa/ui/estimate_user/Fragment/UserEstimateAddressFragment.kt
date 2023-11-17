package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateAddressBinding
import com.example.airconmoa.ui.main_user.MainActivity

class UserEstimateAddressFragment : BaseFragmentVB<FragmentUserEstimateAddressBinding>(
    FragmentUserEstimateAddressBinding::bind, R.layout.fragment_user_estimate_address
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var info1=arguments?.getString("info1").toString()
        Log.d("info1 ",info1)

        with(binding) {
            btnNext.setOnClickListener {
                val bundle = Bundle()
                bundle!!.putString("info1", info1)
                bundle!!.putString("info2",address1.text.toString()+" "+address2.text.toString()+" "+address3.text.toString())
                findNavController().navigate(R.id.action_estimateAddressFragment_to_estimateTypeFragment,bundle)

            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
                while (findNavController().popBackStack());

            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimateAddressFragment_to_estimateUserFragment)

            }

        }
    }
}