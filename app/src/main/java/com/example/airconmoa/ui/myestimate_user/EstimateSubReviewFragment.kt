package com.example.airconmoa.ui.myestimate_user

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentSubEstimateReviewBinding
import org.eazegraph.lib.charts.BarChart
import org.eazegraph.lib.models.BarModel

class EstimateSubReviewFragment : BaseFragmentVB<FragmentSubEstimateReviewBinding>(
    FragmentSubEstimateReviewBinding::bind, R.layout.fragment_sub_estimate_review) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList: ArrayList<DetailReviewItemData> = arrayListOf()
        dataList.apply {
            add(
                DetailReviewItemData("모아", "삼성 · 3대", 5.0, "어제", "꼼꼼하게 해주셔서 너무 좋았어요~!")
            )
            add(
                DetailReviewItemData("모아", "삼성 · 3대", 5.0, "어제", "꼼꼼하게 해주셔서 너무 좋았어요~!")
            )
            add(
                DetailReviewItemData("모아", "삼성 · 3대", 5.0, "어제", "꼼꼼하게 해주셔서 너무 좋았어요~!")
            )
            add(
                DetailReviewItemData("모아", "삼성 · 3대", 5.0, "어제", "꼼꼼하게 해주셔서 너무 좋았어요~!")
            )
            add(
                DetailReviewItemData("모아", "삼성 · 3대", 5.0, "어제", "꼼꼼하게 해주셔서 너무 좋았어요~!")
            )
        }
        with(binding) {
            rvDetailReview.adapter = DetailReviewItemAdapter(dataList)
            rvDetailReview.layoutManager = LinearLayoutManager(context)


            var barChart: BarChart = barChart // barChart 생성

            var bm1=BarModel("5점",1f, 0xFF00D1FF.toInt())
            bm1.setLegendLabel("5점")
            barChart.addBar(bm1)
            barChart.addBar(BarModel("4점",2f, 0xFF00D1FF.toInt()))
            var bm2=BarModel("3점",3f, 0xFF00D1FF.toInt())
            bm2.setLegendLabel("3점")
            barChart.addBar(bm2)
            barChart.addBar(BarModel("2점",4f, 0xFF00D1FF.toInt()))
            var bm3=BarModel("1점",5f, 0xFF00D1FF.toInt())
            bm3.setLegendLabel("1점")
            barChart.addBar(bm3)

            barChart.startAnimation();
        }
    }



}
