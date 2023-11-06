package com.example.airconmoa.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airconmoa.R
import com.example.airconmoa.databinding.ItemRvMainBinding




class CompanyItemDataAdapter(private val dataList: List<CompanyItemData>?) :
    RecyclerView.Adapter<CompanyItemDataAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemRvMainBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val context = viewBinding.root.context

        fun bind(data:CompanyItemData) {

            viewBinding.companyNameTv.text=data.companyName
            viewBinding.contentTv1.text=data.content
            viewBinding.contentTv2.text=data.length

            Glide.with(context)
                .load(data.image)
                .error(R.drawable.png_logo)
                .into(viewBinding.ractangleIv)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemRvMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList!!.get(position))
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


}
