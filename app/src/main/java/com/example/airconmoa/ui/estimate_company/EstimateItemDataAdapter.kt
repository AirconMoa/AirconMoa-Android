package com.example.airconmoa.ui.estimate_company


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa.databinding.ItemRvMainCompanyBinding


class EstimateItemDataAdapter(private val dataList: List<EstimateItemData>?) :
    RecyclerView.Adapter<EstimateItemDataAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemRvMainCompanyBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val context = viewBinding.root.context

        fun bind(data: EstimateItemData) {

            viewBinding.clientNameTv.text=data.clientName
            viewBinding.dateTv.text=data.dateTv
            viewBinding.contentTv1.text=data.content
            viewBinding.contentTv1.text=data.length
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemRvMainCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList!!.get(position))
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}
