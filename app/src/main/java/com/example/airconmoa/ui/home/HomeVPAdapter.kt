package com.example.airconmoa.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserHomeInstallFragment()
            1 -> UserHomeRepairlFragment()
            2 -> UserHomeChkFragment()
            else -> UserHomeInstallFragment()
        }
    }

}