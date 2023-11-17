package com.example.airconmoa.ui.estimate_user.Fragment

import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateTypeBinding
import com.example.airconmoa.ui.join_company.Dialog.LocationRecycleDialog
import com.example.airconmoa.ui.join_company.Dialog.TypeRecycleDialog


class UserEstimateTypeFragment : BaseFragmentVB<FragmentUserEstimateTypeBinding>(
    FragmentUserEstimateTypeBinding::bind, R.layout.fragment_user_estimate_type){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            btnType.setOnClickListener {


                val mydial = TypeRecycleDialog(context!!, this)
                mydial.show()


            }


        }
    }


}