package com.example.airconmoa.ui.home

import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserHomeChkBinding


class UserHomeChkFragment : BaseFragmentVB<FragmentUserHomeChkBinding>(
    FragmentUserHomeChkBinding::bind,
    R.layout.fragment_user_home_chk
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataList: ArrayList<CompanyItemData> = arrayListOf()

    }

}