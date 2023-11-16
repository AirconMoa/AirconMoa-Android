package com.example.airconmoa.ui.mypage_company

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentMyPageCompanyBinding


class MyPageCompanyFragment : BaseFragmentVB<FragmentMyPageCompanyBinding>(
    FragmentMyPageCompanyBinding::bind,
    R.layout.fragment_my_page_company
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mypageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mypageCompanyFragment_to_mypageEditCompanyFragment)
        }
    }
}
