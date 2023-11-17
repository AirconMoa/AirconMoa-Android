package com.example.airconmoa.ui.join_company.Fragment



import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation

import com.example.airconmoa.config.BaseFragmentVB

import com.example.airconmoa.R
import com.example.airconmoa.databinding.FragmentCompanyJoinWhereBinding
import com.example.airconmoa.ui.join_company.CompanyJoinSuccessActivity
import com.example.airconmoa.ui.join_company.Dialog.LocationDoRecycleDialog
import com.example.airconmoa.ui.join_company.Dialog.LocationRecycleDialog
import com.example.airconmoa.ui.join_company.data.CompanyJoinDataSource
import com.example.airconmoa.ui.join_company.data.CompanyJoinView
import com.example.airconmoa.ui.join_company.data.Join
import com.example.airconmoa.ui.join_company.data.ResultCompanyJoin
import com.example.airconmoa.ui.join_user.JoinActivity
import com.example.airconmoa.until.getAddress
import com.example.airconmoa.until.getCompanyName
import com.example.airconmoa.until.getEmail
import com.example.airconmoa.until.getPassword
import com.example.airconmoa.until.getPhonenum
import com.example.airconmoa.until.saveAddress


class CompanyJoinWhereFragment:
    BaseFragmentVB<FragmentCompanyJoinWhereBinding>(FragmentCompanyJoinWhereBinding::bind, R.layout.fragment_company_join_where) {

    private var detail_check = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnLocationSi.setOnClickListener{

                val mydial = LocationRecycleDialog(context!!,this)
                mydial.show()
                check()
            }

            btnLocationDo.setOnClickListener {
                if(txtSi.text == "선택"){
                    showCustomToast("지역을 먼저 선택해 주세요.")
                }else{
                showCustomToast(txtSi.text.toString())
                    val mydial = LocationDoRecycleDialog(context!!,this, txtSi.text.toString())
                    mydial.show()
                }
                check()
            }

            txtLocationDetail.addTextChangedListener(object :TextWatcher{
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
                    if(txtLocationDetail.length()>5){
                        detail_check = true
                        check()
                    }else{
                        detail_check = false
                        check()
                    }
                }

            })
            btnNext.setOnClickListener {
                saveAddress(txtSi.text.toString() + txtDo.text.toString() + txtLocationDetail.text.toString())
                join()
            }

            btnBack.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_companyjoinwhereFragment_to_companyjoinphonenumFragment2)
            }

            btnClose.setOnClickListener {
                startActivity(Intent(activity, JoinActivity::class.java))
                requireActivity().finish()
            }



        }
    }

    private fun check(){
        if(binding.txtSi.text.toString() != ""){
            if(binding.txtDo.text.toString() != ""){
                if(detail_check){
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
        }else{
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(Color.parseColor("#343A40"))
        }
    }


    private fun join(){
        CompanyJoinDataSource().join(getCompanyInfo(),object : CompanyJoinView{
            override fun onLoginSuccess(code: Int, result: ResultCompanyJoin) {
                when(code){
                    1000->{
                        startSuccessActivity()
                    }
                }
            }

            override fun onLoginFailure(message: String?) {
                showCustomToast(message.toString())
            }

        })
    }

    private fun getCompanyInfo() : Join {
        return Join(getEmail()!!, getPassword()!!, getCompanyName()!!, getPhonenum()!!, getAddress()!!)
    }

    private fun startSuccessActivity(){
        startActivity(Intent(activity,CompanyJoinSuccessActivity::class.java))
        requireActivity().finish()
    }
}
