package com.example.airconmoa.ui.myestimate_user

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentEstimateListBinding


class EstimateListFragment : BaseFragmentVB<FragmentEstimateListBinding>(FragmentEstimateListBinding::bind, R.layout.fragment_estimate_list) {
    private var btnClick=false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataList:ArrayList<EstimateListItemData> = arrayListOf()
        dataList.apply{
            add(
                EstimateListItemData("수원제일공업사","경기도 안양시", "500m","132개",4.5,"250,000원")
            )
            add(
                EstimateListItemData("아찔하네","경기도 안양시", "500m","132개",4.5,"220,000원")
            )
            add(
                EstimateListItemData("모아모아모아","경기도 안양시", "500m","132개",4.5,"290,000원")
            )
        }
        with(binding){
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimatelistFragment_to_estimateFragment2)

            }


            with(binding) {
                rvRequestForEstimate.adapter = EstimateListItemDataAdapter(dataList,this@EstimateListFragment)
                rvRequestForEstimate.layoutManager = LinearLayoutManager(context)

                fstBtn.setOnClickListener {
                    if(!btnClick) {
                        secBtn.visibility = View.VISIBLE
                        trdBtn.visibility = View.VISIBLE
                        btnClick=true
                    }
                    else  {
                        secBtn.visibility = View.GONE
                        trdBtn.visibility = View.GONE
                        btnClick=false
                    }
                }

            }
        }
    }

}
