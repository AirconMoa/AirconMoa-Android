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
import com.example.airconmoa.databinding.FragmentCompanyJoinPhonenumBinding
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.until.savePhonenum

class CompanyJoinPhonenumFragment:
    BaseFragmentVB<FragmentCompanyJoinPhonenumBinding>(FragmentCompanyJoinPhonenumBinding::bind, R.layout.fragment_company_join_phonenum)  {

    private var first_check = false
    private var second_check = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){

            firstNum.addTextChangedListener(object :TextWatcher{
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
                    if(firstNum.length() == 3){
                        first_check = true
                        check()
                    }else{
                        first_check = false
                        check()
                    }
                }

            })

            secondNum.addTextChangedListener(object :TextWatcher{
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
                    if(secondNum.length() == 8){
                        second_check = true
                        check()
                    }else{
                        second_check = false
                        check()
                    }
                }

            })

            btnNext.setOnClickListener {
                savePhonenum(firstNum.text.toString() + secondNum.text.toString())
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_companyjoinphonenumFragment_to_companyjoinwhereFragment)
            }

            btnBack.setOnClickListener {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_companyjoinphonenumFragment_to_companyjoincomnameFragment2)
            }

            btnClose.setOnClickListener {
                startActivity(Intent(activity, JoinActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    private fun check(){
        if(first_check){
            if(second_check){
                binding.btnNext.isEnabled = true
                binding.btnNext.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                binding.btnNext.isEnabled = false
                binding.btnNext.setTextColor(Color.parseColor("#343A40"))
            }
        }else{
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(Color.parseColor("#343A40"))
        }
    }
}