package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateBrandBinding
import com.example.airconmoa.ui.estimate_user.EstimateFinishActivity
import com.example.airconmoa.ui.main_user.MainActivity

class UserEstimateBrandFragment : BaseFragmentVB<FragmentUserEstimateBrandBinding>(
    FragmentUserEstimateBrandBinding::bind, R.layout.fragment_user_estimate_brand
) {


    var info6 = ""

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var info1 = arguments?.getString("info1").toString()
        var info2 = arguments?.getString("info2").toString()
        var info3 = arguments?.getString("info3").toString()
        var info4 = arguments?.getInt("info4")
        var info5 = arguments?.getString("info5").toString()
        navController = findNavController()
        with(binding) {
            btnNext.setOnClickListener {
                val intent = Intent(context, EstimateFinishActivity::class.java)
                intent.putExtra("info1", info1)
                intent.putExtra("info2", info2)
                intent.putExtra("info3", info3)
                intent.putExtra("info4", info4)
                intent.putExtra("info5", info5)
                intent.putExtra("info6", info6)
                startActivity(intent)
                requireActivity().finish()
            }
            closeBtn.setOnClickListener { nextActivity(MainActivity::class.java) }
            backBtn.setOnClickListener {
                navController.navigate(R.id.action_estimateBrandFragment_to_estimateDateFragment)
            }
            lgBtn.setOnClickListener { onClickItem(lgBtn, "LG")
            }
            samsungBtn.setOnClickListener {onClickItem(samsungBtn, "삼성")
            }
            carrierBtn.setOnClickListener { onClickItem(carrierBtn, "캐리어") }
            etcBtn.setOnClickListener { onClickItem(etcBtn, "기타") }

        }
    }

    private fun nextActivity(java: Class<*>) {
        val intent = Intent(context, java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TASK }
        startActivity(intent)
        requireActivity().finish()
    }

    private fun onClickItem(view: ImageView, data: String) {
        if (!view.isSelected) {
            view.background =
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.estimate_btn_check_true
                    )
                }
            info6 = data
            view.isSelected = true

        } else {
            view.background =
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.estimate_btn_check_false2
                    )
                }
            view.isSelected = false
        }
    }
}


