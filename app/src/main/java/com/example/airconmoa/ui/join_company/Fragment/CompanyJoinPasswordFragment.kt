package com.example.airconmoa.ui.join_company.Fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinPasswordBinding
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.until.savePassword


class CompanyJoinPasswordFragment :
    BaseFragmentVB<FragmentCompanyJoinPasswordBinding>(
        FragmentCompanyJoinPasswordBinding::bind,
        R.layout.fragment_company_join_password
    ) {

    private var savepassword = ""
    private var nextstep = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            edtPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {

                    if (edtPassword.length() > 5) {
                        btnNext.isEnabled = true
                        btnNext.setTextColor(Color.parseColor("#FFFFFF"))
                    } else {
                        btnNext.isEnabled = false
                        btnNext.setTextColor(Color.parseColor("#343A40"))
                    }


                }


            })
            btnNext.setOnClickListener {
                if (nextstep) {
                    if (edtPassword.text.toString() == savepassword) {
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_companyjoinpasswordFragment_to_companyjoincomnameFragment)
                        savePassword(edtPassword.text.toString())
                    } else {
                        txtPasswordCheck.visibility =  View.VISIBLE
                    }

                } else {
                    btnNext.isEnabled = false
                    btnNext.setTextColor(Color.parseColor("#343A40"))
                    savepassword = edtPassword.text.toString()
                    edtPassword.setText("")
                    edtPassword.hint = "비밀번호를 다시 입력해 주세요"
                    nextstep = true
                }
            }

            btnBack.setOnClickListener {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_companyjoinpasswordFragment_to_companyjoinemailFragment)
            }

            btnClose.setOnClickListener {
                startActivity(Intent(activity, JoinActivity::class.java))
                requireActivity().finish()
            }

        }

    }

}