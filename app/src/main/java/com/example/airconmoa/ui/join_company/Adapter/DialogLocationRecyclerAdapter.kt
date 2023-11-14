package com.example.airconmoa.ui.join_company.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa.ui.join_company.data.SiData
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.DialogLocationRecycleBinding
import com.example.airconmoa_android.databinding.ItemRvLocationBinding


class DialogLocationRecyclerAdapter(val context: Context,
                                    private val list:List<String>,
                                    onClick: OnClickInterface,
                                    onLongClick:OnLongClickInterface)
    :RecyclerView.Adapter<DialogLocationRecyclerAdapter.ViewHolder>(){

    var onClickInterface: OnClickInterface = onClick
    var onLongClickInterface: OnLongClickInterface = onLongClick
    inner class ViewHolder(binding: ItemRvLocationBinding):RecyclerView.ViewHolder(binding.root){
        val location = binding.Seoul
        val thisBinding = binding

        fun onBind(context: Context,item:String,itemPosition:Int){
            location.text = item

            itemView.setOnClickListener(View.OnClickListener {
                onClickInterface.onClick(thisBinding, position = itemPosition)
                Log.d("Tester", "onBind: ddd")
            })
            itemView.setOnLongClickListener(View.OnLongClickListener {
                onLongClickInterface.onLongClick(it, position = itemPosition)
                Log.d("Tester", "onBind: dddddddd")
                return@OnLongClickListener true
            })

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_location,parent,false)

        return ViewHolder(ItemRvLocationBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.onBind(context,item,position)

        holder.location.setOnClickListener(View.OnClickListener {
            onClickInterface.onClick(it,position)
            Log.d("Tester", "onBindViewHolder: adsdasdas${it}")
        })

        holder.location.setOnLongClickListener(View.OnLongClickListener {
            onLongClickInterface.onLongClick(it,position)
            Log.d("Tester", "onBindViewHolder: qweqeqweqw")
            return@OnLongClickListener true
        })
    }

    override fun getItemCount(): Int = list.size

    interface OnClickInterface{
        fun onClick(view: View,position: Int)
        fun onClick(view:ItemRvLocationBinding,position: Int)
    }

    interface OnLongClickInterface{
        fun onLongClick(view:View,position: Int)
        fun onLongClick(view:ItemRvLocationBinding,position: Int)
    }





}