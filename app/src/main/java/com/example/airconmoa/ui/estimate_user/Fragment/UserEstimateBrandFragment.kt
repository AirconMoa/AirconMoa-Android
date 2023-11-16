package com.example.airconmoa.ui.estimate_user.Fragment

import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateBrandBinding
import com.example.airconmoa.ui.join_company.Dialog.LocationRecycleDialog


class UserEstimateBrandFragment: BaseFragmentVB<FragmentUserEstimateBrandBinding>(
    FragmentUserEstimateBrandBinding::bind, R.layout.fragment_user_estimate_brand)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnBrand.setOnClickListener {


                val mydial = LocationRecycleDialog(context!!, this)
                mydial.show()


            }


        }
    }
}