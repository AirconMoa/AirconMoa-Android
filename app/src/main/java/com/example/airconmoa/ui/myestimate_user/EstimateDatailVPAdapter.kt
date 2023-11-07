package com.example.airconmoa.ui.myestimate_user

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class EstimateDatailVPAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> EstimateSubDetailFragment()
            1-> EstimateSubReviewFragment()
            else-> EstimateSubDetailFragment()
        }
    }

}