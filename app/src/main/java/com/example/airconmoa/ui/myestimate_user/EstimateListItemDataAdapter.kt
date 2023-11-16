package com.example.airconmoa.ui.myestimate_user


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa.R
import com.example.airconmoa.databinding.ItemRvReceivedEstimateBinding


class EstimateListItemDataAdapter(
    private val dataList: List<EstimateListItemData>?,
    private val fragment: EstimateListFragment,
) :
    RecyclerView.Adapter<EstimateListItemDataAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemRvReceivedEstimateBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val context = viewBinding.root.context

        fun bind(data: EstimateListItemData) {

            with(viewBinding) {
                addressTv.text = data.address
                lengthTv.text = data.length
                companyNameTv.text = data.companyName
                ratingNumTv.text = data.ratingNum.toString()
                reviewNumTv.text = data.reviewNum
                nextBtn.setOnClickListener {
                    findNavController(fragment).navigate(R.id.action_estimatelistFragment_to_destimatedetailsFragment)
                }
                layout.setOnClickListener {
                    findNavController(fragment).navigate(R.id.action_estimatelistFragment_to_destimatedetailsFragment)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemRvReceivedEstimateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList!!.get(position))
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}
