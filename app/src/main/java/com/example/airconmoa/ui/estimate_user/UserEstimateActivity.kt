package com.example.airconmoa.ui.estimate_user

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateAddressFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateBrandFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateDateFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateQuantityFragment
import com.example.airconmoa.ui.estimate_user.Fragment.UserEstimateTypeFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinCNFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinCompanynameFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinNameFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinPasswordFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinPhonenumFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinTermFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyJoinWhereFragment
import com.example.airconmoa.ui.join_company.Fragment.CompanyjoinEmailFragment

import com.example.airconmoa.databinding.ActivityUserEstimateBinding

class UserEstimateActivity: BaseActivityVB<ActivityUserEstimateBinding>(ActivityUserEstimateBinding::inflate) {

    private var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = ArrayList<Fragment>()
        list.add(UserEstimateFragment())
        list.add(UserEstimateAddressFragment())
        list.add(UserEstimateTypeFragment())
        list.add(UserEstimateQuantityFragment())
        list.add(UserEstimateDateFragment())
        list.add(UserEstimateBrandFragment())

        with(binding){

            supportFragmentManager
                .beginTransaction()
                .replace(userEstimateFrame.id, UserEstimateFragment())
                .commitAllowingStateLoss()

            btnNext.setOnClickListener {
                if(counter > 7){
                    //success 이동
                }

                //약간 어색.. 끝날때 애니메이션 추가해야할듯
                ObjectAnimator.ofFloat(userEstimateFrame,"translationX",1000f,0f).apply {
                    duration = 500
                    supportFragmentManager
                        .beginTransaction()
                        .replace(userEstimateFrame.id,list.get(counter))
                        .commitAllowingStateLoss()
                    counter += 1
                    start()
                }

            }

        }



    }
}