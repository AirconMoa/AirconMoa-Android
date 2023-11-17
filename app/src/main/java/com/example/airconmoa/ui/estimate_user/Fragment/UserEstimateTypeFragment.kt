package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateTypeBinding
import com.example.airconmoa.ui.main_user.MainActivity


class UserEstimateTypeFragment : BaseFragmentVB<FragmentUserEstimateTypeBinding>(
    FragmentUserEstimateTypeBinding::bind, R.layout.fragment_user_estimate_type
) {

    var installType = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var info1 = arguments?.getString("info1").toString()
        var info2 = arguments?.getString("info2").toString()

        Log.d("info1", info1)
        Log.d("info1", info2)

        with(binding) {

            Log.d("info1", apt.isSelected.toString())
            nextTypeBtn.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("info1", info1)
                bundle.putString("info2", info2)
                bundle.putString("info3", installType)
                findNavController().navigate(
                    R.id.action_estimateTypeFragment_to_estimateQuantityFragment,
                    bundle
                )

            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
                while (findNavController().popBackStack());
            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimateTypeFragment_to_estimateAddressFragment)
            }

            Log.d("info1", apt.isSelected.toString())

            apt.setOnClickListener {
                onClickItem(apt, "아파트")
            }
            house.setOnClickListener {
                onClickItem(house, "주택")
            }

            building.setOnClickListener {
                onClickItem(building, "빌딩")
            }

            buila.setOnClickListener {
                onClickItem(buila, "빌라")
            }

            etc.setOnClickListener {
                onClickItem(etc, "기타")
            }

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
            installType = data
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
