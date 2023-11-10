package com.example.airconmoa.ui.join_company.Adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.DialogLocationRecycleBinding
import com.example.airconmoa_android.databinding.ItemRvLocationBinding


class DialogLocationRecyclerAdapter():RecyclerView.Adapter<DialogLocationRecyclerAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemRvLocationBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_location,parent,false)

        return ViewHolder(ItemRvLocationBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.Seoul.setText(locationlist[position])
    }

    override fun getItemCount(): Int = locationlist.size

    val locationlist : List<String> = listOf(
        "Seoul",
        "Incheon",
        "Busan",
        "few",
        "ads",
        "reqgqer",
        "qrv",
        "erqbr",
        "vqererv",
        "zxcvzx"
    )



}