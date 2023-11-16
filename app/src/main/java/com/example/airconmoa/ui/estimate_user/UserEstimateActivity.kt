package com.example.airconmoa.ui.estimate_user

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityUserEstimateBinding

class UserEstimateActivity: BaseActivityVB<ActivityUserEstimateBinding>(ActivityUserEstimateBinding::inflate) {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame_estimate) as NavHostFragment
        navController = navHostFragment.navController

        //navController.navigate(R.id.estimateUserFragment)
    }



   /* private var counter = 1
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

            /*btnNext.setOnClickListener {
                if(counter<=6) {
                    //약간 어색.. 끝날때 애니메이션 추가해야할듯
                    ObjectAnimator.ofFloat(userEstimateFrame, "translationX", 1000f, 0f).apply {
                        duration = 500
                        if(counter<=6) {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(userEstimateFrame.id, list.get(counter))
                                .commitAllowingStateLoss()
                            counter += 1
                            start()
                        }
                        Log.d("확인 ","{$counter}")
                    }
                    Log.d("확인2 ","{$counter}")
                    estimateProgressText.text="$counter/7"
                    estimateProgress.setProgress(counter)
                }
                else if(counter >= 7){
                    //success 이동
                    val intent= Intent(this@UserEstimateActivity,EstimateFinishActivity::class.java)
                    startActivity(intent)
                    Log.d("확인1 ","확인")
                    Log.d("확인 ","{$counter}")
                }
            }
             */

        }






    }

    */
}