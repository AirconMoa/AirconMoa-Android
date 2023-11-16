package com.example.airconmoa.ui.join_company.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa.ui.join_company.Adapter.DialogLocationRecyclerAdapter
import com.example.airconmoa.ui.join_company.data.SiData
import com.example.airconmoa.ui.login_user.model.LoginPostData
import com.example.airconmoa.until.saveSi
import com.example.airconmoa_android.databinding.DialogBrandRecycleBinding
import com.example.airconmoa_android.databinding.DialogLocationRecycleBinding
import com.example.airconmoa_android.databinding.DialogTypeRecycleBinding
import com.example.airconmoa_android.databinding.FragmentUserEstimateBrandBinding
import com.example.airconmoa_android.databinding.FragmentUserEstimateTypeBinding
import com.example.airconmoa_android.databinding.ItemRvLocationBinding


class BrandRecycleDialog(context: Context, dlgbinding: FragmentUserEstimateBrandBinding): Dialog(context){

    private lateinit var binding: DialogBrandRecycleBinding


    val obj = object : DialogLocationRecyclerAdapter.OnClickInterface{
        override fun onClick(view: View, position: Int) {
            Log.d("Tester", "onClick: ${locationlist[position]}")
            dlgbinding.txtBrand.text = locationlist[position]
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
        binding = DialogBrandRecycleBinding.inflate(layoutInflater)
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
        "아파트", "주택", "빌라", "빌딩",
        "기타"
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

