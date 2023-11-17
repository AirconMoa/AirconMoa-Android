package com.example.airconmoa.ui.join_company

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityCompanyJoinBinding
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinCNFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinCompanynameFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinNameFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinPasswordFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinPhonenumFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinTermFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinWhereFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyjoinEmailFragment


class CompanyJoinActivity :
    BaseActivityVB<ActivityCompanyJoinBinding>(ActivityCompanyJoinBinding::inflate) {

    private var counter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fragment list에 넣기
        val list = ArrayList<Fragment>()
        list.add(CompanyJoinTermFragment())
        list.add(CompanyJoinNameFragment())
        list.add(CompanyJoinPasswordFragment())
        list.add(CompanyJoinCompanynameFragment())
        list.add(CompanyJoinPhonenumFragment())
        list.add(CompanyjoinEmailFragment())
        list.add(CompanyJoinCNFragment())
        list.add(CompanyJoinWhereFragment())

        val progress = findViewById<ProgressBar>(R.id.estimate_progress)



        with(binding) {
            supportFragmentManager
                .beginTransaction()
                .replace(companyJoinFrame.id, CompanyJoinTermFragment())
                .commitAllowingStateLoss()


            btnNext.setOnClickListener {
                if (counter == 7) {
                    val intent =
                        Intent(this@CompanyJoinActivity, CompanyJoinSuccessActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //약간 어색.. 끝날때 애니메이션 추가해야할듯
                    Log.d("Tester", "onCreate: ${counter}")
                    ObjectAnimator.ofFloat(companyJoinFrame, "translationX", 1000f, 0f).apply {
                        duration = 500
                        supportFragmentManager
                            .beginTransaction()
                            .replace(companyJoinFrame.id, list.get(++counter))
                            .commitAllowingStateLoss()
                        start()
                    }

                }


                progress.progress = counter

            }

            btnBack.setOnClickListener {
                ObjectAnimator.ofFloat(companyJoinFrame, "translationX", -1000f, 0f).apply {
                    duration = 500
                    supportFragmentManager
                        .beginTransaction()
                        .replace(companyJoinFrame.id, list.get(--counter))
                        .commitAllowingStateLoss()

                    start()
                }
            }


        }


    }
}