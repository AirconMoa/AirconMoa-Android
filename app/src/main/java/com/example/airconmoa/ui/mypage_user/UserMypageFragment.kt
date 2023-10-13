package com.example.airconmoa.ui.mypage_user

import android.os.Bundle
import android.view.View
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.FragmentUserMypageBinding
import androidx.navigation.fragment.findNavController

class UserMypageFragment : BaseFragmentVB<FragmentUserMypageBinding>(FragmentUserMypageBinding::bind, R.layout.fragment_user_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mypageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_mypageeditFragment)
        }
    }

}