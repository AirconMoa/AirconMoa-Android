package com.example.airconmoa.ui.join_company.Fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinNameBinding
import com.example.airconmoa.until.saveName


class CompanyJoinNameFragment:
    BaseFragmentVB<FragmentCompanyJoinNameBinding>(FragmentCompanyJoinNameBinding::bind, R.layout.fragment_company_join_name)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            edtName.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    txtMaxChecker.text = edtName.length().toString() + "/10"

                }

                override fun afterTextChanged(s: Editable?) {
                    if(edtName.length()>2){
                        btnNext.isEnabled = true
                        btnNext.setTextColor(Color.parseColor("#FFFFFF"))
                    }else{
                        btnNext.isEnabled = false
                        btnNext.setTextColor(Color.parseColor("#343A40"))
                    }
                }

            })

            btnNext.setOnClickListener {
                saveName(edtName.text.toString())

            }
        }
    }
}