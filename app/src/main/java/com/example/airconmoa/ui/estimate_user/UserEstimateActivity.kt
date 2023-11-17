package com.example.airconmoa.ui.estimate_user

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateAddressFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateBrandFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateDateFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateQuantityFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateTypeFragment


import com.example.airconmoa.databinding.ActivityUserEstimateBinding

class UserEstimateActivity: BaseActivityVB<ActivityUserEstimateBinding>(ActivityUserEstimateBinding::inflate) {

    private var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = ArrayList<Fragment>()
        list.add(UserEstimateFragment())
        list.add(UserEstimateAddressFragment())
        list.add(UserEstimateTypeFragment())
        list.add(UserEstimateQuantityFragment())
        list.add(UserEstimateDateFragment())
        list.add(UserEstimateBrandFragment())




    }
}