package com.example.airconmoa.ui.join_company.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import android.graphics.Color
import android.widget.CompoundButton
import androidx.core.graphics.toColorFilter
import androidx.navigation.Navigation
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinTermsBinding
import com.example.airconmoa.ui.join_company.CompanyJoinSuccessActivity
import com.example.airconmoa.ui.join_user.JoinActivity


class CompanyJoinTermFragment : BaseFragmentVB<FragmentCompanyJoinTermsBinding>(
    FragmentCompanyJoinTermsBinding::bind,
    R.layout.fragment_company_join_terms
) {

    private var check_all = false
    private var essen_check_all = false


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            checkall.setOnClickListener { onCheckAll(checkall) }
            check1.setOnClickListener{onCheckAll(check1)}
            check2.setOnClickListener{onCheckAll(check2)}
            check3.setOnClickListener{onCheckAll(check3)}
            check4.setOnClickListener{onCheckAll(check4)}
            check5.setOnClickListener{onCheckAll(check5)}
            check6.setOnClickListener{onCheckAll(check6)}
            check7.setOnClickListener{onCheckAll(check7)}


            btnNext.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_companyjointermFragment_to_companyjoinemailFragment)
            }

            btnBack.setOnClickListener {
                startActivity(Intent(activity, JoinActivity::class.java))
                requireActivity().finish()
            }

            btnClose.setOnClickListener {
                startActivity(Intent(activity,JoinActivity::class.java))
                requireActivity().finish()
            }
        }


    }//onViewCreated

    private fun onCheckAll(compoundButton: CompoundButton) {
        when (compoundButton.id) {
            R.id.checkall -> {
                if (binding.checkall.isChecked) {
                    binding.check1.isChecked = true
                    binding.check2.isChecked = true
                    binding.check3.isChecked = true
                    binding.check4.isChecked = true
                    binding.check5.isChecked = true
                    binding.check6.isChecked = true
                    binding.check7.isChecked = true
                    check_all = true
                    binding.card.setCardBackgroundColor(Color.parseColor("#CCF6FF"))
                    essen_check_all = (
                            binding.check1.isChecked &&
                                    binding.check2.isChecked &&
                                    binding.check3.isChecked &&
                                    binding.check4.isChecked &&
                                    binding.check7.isChecked
                            )
                    checkNext()
                } else {
                    binding.check1.isChecked = false
                    binding.check2.isChecked = false
                    binding.check3.isChecked = false
                    binding.check4.isChecked = false
                    binding.check5.isChecked = false
                    binding.check6.isChecked = false
                    binding.check7.isChecked = false
                    check_all = false
                    binding.card.setCardBackgroundColor(Color.parseColor("#CDD3D9"))
                    essen_check_all = (
                            binding.check1.isChecked &&
                                    binding.check2.isChecked &&
                                    binding.check3.isChecked &&
                                    binding.check4.isChecked &&
                                    binding.check7.isChecked
                            )
                    checkNext()
                }
            }else -> {
                binding.checkall.isChecked = (
                    binding.check1.isChecked &&
                            binding.check2.isChecked &&
                            binding.check3.isChecked &&
                            binding.check4.isChecked &&
                            binding.check5.isChecked &&
                            binding.check6.isChecked &&
                            binding.check7.isChecked
                )
                essen_check_all = (
                        binding.check1.isChecked &&
                                binding.check2.isChecked &&
                                binding.check3.isChecked &&
                                binding.check4.isChecked &&
                                binding.check7.isChecked
                        )
                if(binding.checkall.isChecked){
                    binding.card.setCardBackgroundColor(Color.parseColor("#CCF6FF"))
                }else{
                    binding.card.setCardBackgroundColor(Color.parseColor("#CDD3D9"))
                }
            checkNext()
            }
        }
    }
    private fun checkNext(){

        if(essen_check_all){
            binding.btnNext.isEnabled = true
            binding.btnNext.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(Color.parseColor("#343A40"))
        }
    }
}