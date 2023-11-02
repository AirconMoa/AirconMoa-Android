package com.example.airconmoa.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ItemRvEstimateBinding




class RequestForEstimateItemDataAdapter(
    private val dataList: List<RequestForEstimateItemData>?,
    private val fragment2: UserEstimateFragment2
) :
    RecyclerView.Adapter<RequestForEstimateItemDataAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemRvEstimateBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val context = viewBinding.root.context

        fun bind(data: RequestForEstimateItemData) {

            with(viewBinding) {
                estimateNumber.text = data.estmateNum.toString()
                addressTv.text = data.address
                housingTypeTv.text = data.housingType
                brandTv.text=data.brandName
                airconNumTv.text=data.airconNum.toString()
                dateTv.text=data.dateNum
                nextBtn.setOnClickListener {
                    findNavController(fragment2).navigate(R.id.action_estimateFragment2_to_estimatelistFragment)
                }
                layout.setOnClickListener {
                    findNavController(fragment2).navigate(R.id.action_estimateFragment2_to_estimatelistFragment)
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemRvEstimateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList!!.get(position))
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}
