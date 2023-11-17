package com.example.airconmoa.ui.estimate_company


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airconmoa.R
import com.example.airconmoa.databinding.ItemRvMainCompanyBinding
import com.example.airconmoa.ui.estimate_company.model.EstimateItemData
import com.example.airconmoa.ui.estimate_company.model.GetRequestEstimateRes


class EstimateItemDataAdapter(private val dataList: List<GetRequestEstimateRes>?,
                              private val fragment: EstimateCompanyFragment,
) :
    RecyclerView.Adapter<EstimateItemDataAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemRvMainCompanyBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val context = viewBinding.root.context

        fun bind(data: GetRequestEstimateRes) {

            with(viewBinding) {
                clientNameTv.text = data.userNickname
                dateTv.text = data.installationDate
                when (data.installInfo) {
                    "NEW_BUILDING" -> contentTv1.text = "신규 설치 ∙ 신규 건물"
                    "EXISTING_BUILDING" -> contentTv1.text = "신규 설치 ∙ 기존 건물"
                    "AIRCON_REPLACEMENT" -> contentTv1.text = "에어컨 교체"
                    else -> contentTv1.text = "기타 설치 정보"
                }
                // contentTv2.text = data.length

                Glide.with(context)
                    .load(data.porfileUrl)
                    .error(R.drawable.detail_review_user_profile)
                    .into(viewBinding.ractangleIv)

                clientNameTv.setOnClickListener {
                    findNavController(fragment).navigate(R.id.action_estimateCompanyFragment_to_estimateDetailCompanyFragment )
                }
                radioBtn.setOnClickListener {
                    Glide.with(context)
                        .load(R.drawable.user_profile_gray)
                        .error(R.drawable.detail_review_user_profile)
                        .into(viewBinding.ractangleIv)

                    mainLayout.setBackgroundResource(R.drawable.shape_btn_stroke_gray3)
                    viewLine.setCardBackgroundColor(ContextCompat.getColor(context, R.color.airconmoa_gray3))
                    clientNameTv.setTextColor(ContextCompat.getColor(context,R.color.airconmoa_gray3))
                    dateTv.setTextColor(ContextCompat.getColor(context,R.color.airconmoa_gray3))
                    contentTv1.setTextColor(ContextCompat.getColor(context,R.color.airconmoa_gray3))
                    contentTv2.setTextColor(ContextCompat.getColor(context,R.color.airconmoa_gray3))
                    radioBtn.visibility=View.INVISIBLE
                    hideEditTv.visibility= View.VISIBLE
                    hideEditTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
                }
                hideEditTv.setOnClickListener {
                    findNavController(fragment).navigate(R.id.action_estimateCompanyFragment_to_estimateEditCompanyFragment)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemRvMainCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList!!.get(position))
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    interface OnItemClickListener {
        fun onItemClick(getRequestEstimateRes : GetRequestEstimateRes)
    }

    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
}
