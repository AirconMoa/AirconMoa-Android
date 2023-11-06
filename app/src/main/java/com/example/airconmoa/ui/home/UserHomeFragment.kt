package com.example.airconmoa.ui.home

import android.os.Bundle
import android.view.View
import com.example.airconmoa.config.BaseFragmentVB
import com.google.android.material.tabs.TabLayoutMediator
import com.example.airconmoa.R
import com.example.airconmoa.databinding.FragmentUserHomeBinding

class UserHomeFragment : BaseFragmentVB<FragmentUserHomeBinding>(FragmentUserHomeBinding::bind, R.layout.fragment_user_home) {
    private val tabTitle= arrayListOf("설치","수리","점검")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeVPAdapter= HomeVPAdapter(this@UserHomeFragment)
        binding.vpHome.adapter=homeVPAdapter

        TabLayoutMediator(binding.tabMain,binding.vpHome){
                tab,position->
            tab.text=tabTitle[position]
        }.attach()
    }

}