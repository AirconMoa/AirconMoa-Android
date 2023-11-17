package com.example.airconmoa.ui.join_company.Fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinEmailBinding
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.until.saveEmail


class CompanyjoinEmailFragment:
    BaseFragmentVB<FragmentCompanyJoinEmailBinding>(FragmentCompanyJoinEmailBinding::bind, R.layout.fragment_company_join_email)  {

    private var firstCheck = false
    private var secondCheck = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            edtEmailId.addTextChangedListener(object :TextWatcher{
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
                    if(edtEmailId.length()>4){
                        firstCheck = true
                        check()
                    }else{
                        firstCheck = false
                        check()
                    }
                }

            })

            edtEmailPortal.addTextChangedListener(object :TextWatcher{
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
                    if(edtEmailPortal.length()>4){
                        secondCheck = true
                        check()
                    }else{
                        secondCheck = false
                        check()
                    }
                }

            })

            btnNext.setOnClickListener {
                Log.d("Tester", "onViewCreated: ${edtEmailId.text.toString() + "@" + edtEmailPortal.text.toString()}")
                saveEmail(edtEmailId.text.toString() + "@" + edtEmailPortal.text.toString())
                Navigation.findNavController(binding.root).navigate(R.id.action_companyjoinemailFragment_to_companyjoinpasswordFragment)
            }

            btnBack.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_companyjoinemailFragment_to_companyjointermFragment)
            }

            btnClose.setOnClickListener {
                startActivity(Intent(activity, JoinActivity::class.java))
                requireActivity().finish()
            }

        }
    } // onViewCreate

    private fun check(){
        if(firstCheck){
            if(secondCheck){
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