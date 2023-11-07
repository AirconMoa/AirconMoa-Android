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

            val entries = ArrayList<BarModel>()
            barChart.addBar(BarModel(1f, R.color.airconmoa_main))
            barChart.addBar(BarModel(2f, R.color.airconmoa_main))
            barChart.addBar(BarModel(3f, R.color.airconmoa_main))
            barChart.addBar(BarModel(4f, R.color.airconmoa_main))
            barChart.addBar(BarModel(5f, R.color.airconmoa_main))

            barChart.startAnimation();
        }
    }



}
