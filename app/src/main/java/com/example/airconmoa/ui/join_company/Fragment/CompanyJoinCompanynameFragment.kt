package com.example.airconmoa.ui.join_company.Fragment

import android.content.Intent
import android.graphics.Color
import com.example.airconmoa.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinCompanynameBinding
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.until.saveCompanyName


class CompanyJoinCompanynameFragment  :
    BaseFragmentVB<FragmentCompanyJoinCompanynameBinding>(FragmentCompanyJoinCompanynameBinding::bind, R.layout.fragment_company_join_companyname){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            edtCompanyName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    txtMaxChecker.text = edtCompanyName.length().toString() + "/10"
                }

                override fun afterTextChanged(s: Editable?) {
                    if(edtCompanyName.length()>2){
                        btnNext.isEnabled = true
                        btnNext.setTextColor(Color.parseColor("#FFFFFF"))
                    }else{
                        btnNext.isEnabled = false
                        btnNext.setTextColor(Color.parseColor("#343A40"))
                    }
                }

            })

            btnNext.setOnClickListener {
                saveCompanyName(edtCompanyName.text.toString())
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_companyjoincomnameFragment_to_companyjoinphonenumFragment)
            }

            btnNext.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_companyjoincomnameFragment_to_companyjoinpasswordFragment)
            }

            btnClose.setOnClickListener {
                startActivity(Intent(activity, JoinActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}