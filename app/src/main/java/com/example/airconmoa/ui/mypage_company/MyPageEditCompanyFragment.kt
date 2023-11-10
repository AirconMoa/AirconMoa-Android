package com.example.airconmoa.ui.mypage_company

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentMyPageEditCompanyBinding


class MyPageEditCompanyFragment : BaseFragmentVB<FragmentMyPageEditCompanyBinding>(FragmentMyPageEditCompanyBinding::bind, R.layout.fragment_my_page_edit_company) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            nameeditTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (nameeditTv.length() > 0) {
                        editCloseBtn1.visibility = View.VISIBLE
                    } else {
                        editCloseBtn1.visibility = View.GONE
                    }

                }
            })

            phoneeditTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (phoneeditTv.length() > 0) {
                        editCloseBtn2.visibility = View.VISIBLE
                    } else {
                        editCloseBtn2.visibility = View.GONE
                    }

                }
            })
            nicknameeditTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (nicknameeditTv.length() > 0) {
                        editCloseBtn3.visibility = View.VISIBLE
                    } else {
                        editCloseBtn3.visibility = View.GONE
                    }

                }
            })
            editCloseBtn1.setOnClickListener { nameeditTv.text=null }
            editCloseBtn2.setOnClickListener {  phoneeditTv.text=null }
            editCloseBtn3.setOnClickListener {  nicknameeditTv.text=null }

            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_mypageEditCompanyFragment_to_mypageCompanyFragment)

            }
        }

    }
}