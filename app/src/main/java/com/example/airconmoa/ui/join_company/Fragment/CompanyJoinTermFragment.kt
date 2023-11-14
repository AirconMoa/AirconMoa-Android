package com.example.airconmoa.ui.join_company.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import android.graphics.Color
import androidx.core.graphics.toColorFilter
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinTermsBinding


class CompanyJoinTermFragment :BaseFragmentVB<FragmentCompanyJoinTermsBinding>(FragmentCompanyJoinTermsBinding::bind, R.layout.fragment_company_join_terms) {

    private var essen1 = false
    private var essen2 = false
    private var essen3 = false
    private var essen4 = false
    private var essen5 = false
    private var sel1 = false
    private var sel2 = false
    private var check_all = false
    private var essen_check_all = false


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            estimateTermsCheckBox.setOnClickListener {
                if(check_all){
                    check_all = false
                    essen1 = false
                    essen2 = false
                    essen3 = false
                    essen4 = false
                    essen5 = false
                    sel1 = false
                    sel2 = false
                    card.setCardBackgroundColor(Color.parseColor("#CDD3D9"))
                    checkall.setImageResource(R.drawable.estimate_btn_check_false)
                    check1.setImageResource(R.drawable.estimate_btn_check_small_false)
                    check2.setImageResource(R.drawable.estimate_btn_check_small_false)
                    check3.setImageResource(R.drawable.estimate_btn_check_small_false)
                    check4.setImageResource(R.drawable.estimate_btn_check_small_false)
                    check5.setImageResource(R.drawable.estimate_btn_check_small_false)
                    check6.setImageResource(R.drawable.estimate_btn_check_small_false)
                    check7.setImageResource(R.drawable.estimate_btn_check_small_false)

                }else{
                    check_all = true
                    essen1 = true
                    essen2 = true
                    essen3 = true
                    essen4 = true
                    essen5 = true
                    sel1 = true
                    sel2 = true
                    card.setCardBackgroundColor(Color.parseColor("#CCF6FF"))
                    checkall.setImageResource(R.drawable.estimate_btn_check_true)
                    check1.setImageResource(R.drawable.estimate_btn_check_small_true)
                    check2.setImageResource(R.drawable.estimate_btn_check_small_true)
                    check3.setImageResource(R.drawable.estimate_btn_check_small_true)
                    check4.setImageResource(R.drawable.estimate_btn_check_small_true)
                    check5.setImageResource(R.drawable.estimate_btn_check_small_true)
                    check6.setImageResource(R.drawable.estimate_btn_check_small_true)
                    check7.setImageResource(R.drawable.estimate_btn_check_small_true)
                }

            }

            essential1.setOnClickListener {
                essen1 = essen1 != true
                check_all = false

            }

            essential2.setOnClickListener {
                essen2 = essen2 != true
                check_all = false
            }

            essential3.setOnClickListener {
                essen3 = essen3 != true
                check_all = false
            }

            essential4.setOnClickListener {
                essen4 = essen4 != true
                check_all = false
            }

            essential5.setOnClickListener {
                essen5 = essen5 != true
                check_all = false
            }

            select1.setOnClickListener {
                sel1 = sel1 != true
                check_all = false
            }

            select2.setOnClickListener {
                sel2 = sel2 != true
                check_all = false
            }


        }


    }


}