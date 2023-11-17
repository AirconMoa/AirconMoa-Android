package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateBinding
import com.example.airconmoa.ui.main_user.MainActivity

class UserEstimateFragment : BaseFragmentVB<FragmentUserEstimateBinding>(
    FragmentUserEstimateBinding::bind,
    R.layout.fragment_user_estimate
) {
    var installContent = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnNext.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("info1", installContent)
                findNavController().navigate(
                    R.id.action_estimateUserFragment_to_estimateAddressFragment,
                    bundle
                )

            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
                while (findNavController().popBackStack());

            }
            backBtn.setOnClickListener {
                while (findNavController().popBackStack());

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
            }

            imgNew.setOnClickListener { onClickItem(imgNew, "에어컨 신규 설치 / 신규 건물") }
            imgChange.setOnClickListener { onClickItem(imgChange, "에어컨 교체") }
            imgExist.setOnClickListener { onClickItem(imgExist, "에어컨 신규 설치 / 기존 건물") }
        }


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
            installContent = data
            view.isSelected=true

        } else {
            view.background =
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.estimate_btn_check_false2
                    )
                }
            view.isSelected=false
        }
    }
}