package com.example.airconmoa.ui.home

import android.os.Bundle
import android.view.View
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.FragmentUserHomeBinding

class UserHomeFragment : BaseFragmentVB<FragmentUserHomeBinding>(FragmentUserHomeBinding::bind, R.layout.fragment_user_home) {
    private val tabTitle= arrayListOf("first","second","subThird")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}