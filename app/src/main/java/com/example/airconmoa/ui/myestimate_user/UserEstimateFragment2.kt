package com.example.airconmoa.ui.myestimate_user

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimate2Binding


class UserEstimateFragment2 : BaseFragmentVB<FragmentUserEstimate2Binding>(
    FragmentUserEstimate2Binding::bind,
    R.layout.fragment_user_estimate2
) {

    private var btnClick = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList: ArrayList<RequestForEstimateItemData> = arrayListOf()
        dataList.apply {
            add(
                RequestForEstimateItemData(211378, "인천광역시 미추홀구", "아파트", "LG", 2, "2023.11.19")
            )
            add(
                RequestForEstimateItemData(211234, "경기도 안양시", "아파트", "LG", 1, "2023.09.11")
            )
            add(
                RequestForEstimateItemData(211132, "경기도 안양시", "아파트", "LG", 1, "2023.08.24")
            )
            add(
                RequestForEstimateItemData(210234, "경기도 안양시", "아파트", "LG", 1, "2023.08.03")
            )
            add(
                RequestForEstimateItemData(210235, "인천광역시", "아파트", "LG", 1, "2023.11.03")
            )
        }

        with(binding) {
            rvRequestForEstimate.adapter =
                RequestForEstimateItemDataAdapter(dataList, this@UserEstimateFragment2)
            rvRequestForEstimate.layoutManager = LinearLayoutManager(context)

            fstBtn.setOnClickListener {
                if (!btnClick) {
                    secBtn.visibility = View.VISIBLE
                    trdBtn.visibility = View.VISIBLE
                    btnClick = true
                } else {
                    secBtn.visibility = View.GONE
                    trdBtn.visibility = View.GONE
                    btnClick = false
                }
            }

        }
    }

}