package com.example.airconmoa.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserHomeInstallBinding


class UserHomeInstallFragment : BaseFragmentVB<FragmentUserHomeInstallBinding>(FragmentUserHomeInstallBinding::bind, R.layout.fragment_user_home_install) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val dataList:ArrayList<CompanyItemData> = arrayListOf()
        dataList.apply{
            add(
                CompanyItemData("수원제일공업사","후기 12 · 시공만족도 95%","500m", R.drawable.duck_png)
            )
            add(
                CompanyItemData("수원제일공업사","후기 12 · 시공만족도 95%","500m",R.drawable.duck_png)
            )
            add(
                CompanyItemData("수원제일공업사","후기 12 · 시공만족도 95%","500m",R.drawable.duck_png)
            )
        }
        binding.rvMain.adapter= CompanyItemDataAdapter(dataList)
        binding.rvMain.layoutManager= LinearLayoutManager(context)
    }

}