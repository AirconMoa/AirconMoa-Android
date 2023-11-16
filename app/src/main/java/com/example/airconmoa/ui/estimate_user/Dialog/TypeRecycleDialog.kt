package com.example.airconmoa.ui.join_company.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.airconmoa.databinding.DialogTypeRecycleBinding
import com.example.airconmoa.databinding.FragmentUserEstimateTypeBinding
import com.example.airconmoa.databinding.ItemRvLocationBinding
import com.example.airconmoa.ui.join_company.Adapter.DialogLocationRecyclerAdapter



class TypeRecycleDialog(context: Context, dlgbinding: FragmentUserEstimateTypeBinding): Dialog(context){

    private lateinit var binding: DialogTypeRecycleBinding


    val obj = object : DialogLocationRecyclerAdapter.OnClickInterface{
        override fun onClick(view: View, position: Int) {
            Log.d("Tester", "onClick: ${locationlist[position]}")
            dlgbinding.txtType.text = locationlist[position]
            //기타 누르면 입력창 나오는건 grid 이해 부족으로 일단 보류
            dismiss()
        }

        override fun onClick(view: ItemRvLocationBinding, position: Int) {

        }

    }
    val obj2 = object :DialogLocationRecyclerAdapter.OnLongClickInterface{
        override fun onLongClick(view: View, position: Int) {

        }

        override fun onLongClick(view: ItemRvLocationBinding, position: Int) {

        }

    }
    val itemClickInterface:DialogLocationRecyclerAdapter.OnClickInterface = obj
    val itemLongClickInterface:DialogLocationRecyclerAdapter.OnLongClickInterface = obj2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogTypeRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listadapter = DialogLocationRecyclerAdapter(context ,
            locationlist,itemClickInterface,itemLongClickInterface)

        binding.locationRecyclerView.adapter = listadapter

        var listManager = GridLayoutManager(context,5)


        binding.locationRecyclerView.apply {
            layoutManager = listManager
        }

        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.BOTTOM)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

    }


    val locationlist : List<String> = listOf(
        "LG", "삼성", "Carrier", "기타"
    )
    /*
        fun showDlg(){
            binding = DialogLocationRecycleBinding.inflate(context.layoutInflater)

            binding.locationRecyclerView.adapter=DialogLocationRecyclerAdapter()

            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dlg.setContentView(binding.root)
            dlg.setCancelable(true)
            dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dlg.window?.setGravity(Gravity.BOTTOM)
            dlg.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dlg.show()
            binding.locationRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    val child = rv.findChildViewUnder(e.x,e.y)
                    Log.d("Tester", "onInterceptTouchEvent: ${child}")

                    if(child != null){
                        val position = rv.getChildAdapterPosition(child)
                        val view = rv.layoutManager?.findViewByPosition(position)

                        Log.d("Tester", "onInterceptTouchEvent: dsadssdd ${view.toString()}")

                        view?.setOnClickListener {
                            onClickListener.onClicked(position)
                            Log.d("Tester", "onInterceptTouchEvent: ddddddd")

                            dlg.dismiss()
                        }

                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

                }

            })
        }

        interface ButtonClickListener{
            fun onClicked(index:Int?)
        }

        private lateinit var onClickListener:ButtonClickListener
        fun setOnClickedListener(listener:ButtonClickListener){
            onClickListener = listener
        }

    */
}

