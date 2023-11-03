package com.example.airconmoa.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa_android.databinding.ItemRvDetailReviewBinding




class DetailReviewItemAdapter(
    private val dataList: List<DetailReviewItemData>?
) :
    RecyclerView.Adapter<DetailReviewItemAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemRvDetailReviewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val context = viewBinding.root.context

        fun bind(data: DetailReviewItemData) {

            with(viewBinding) {

                userNameTv.text=data.userName
                brandAndNumTv.text=data.brandAndNum
                ratingBar.rating=data.ratingNum.toFloat()
                timeHistoryTv.text=data.historyDate
                reviewContentTv.text=data.reviewContent
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemRvDetailReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList!!.get(position))
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}
