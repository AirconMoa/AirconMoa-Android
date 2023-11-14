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
import com.example.airconmoa.until.Constants
import com.example.airconmoa.until.saveSi
import com.example.airconmoa_android.databinding.DialogLocationRecycleBinding
import com.example.airconmoa_android.databinding.FragmentCompanyJoinWhereBinding
import com.example.airconmoa_android.databinding.ItemRvLocationBinding


class LocationRecycleDialog(context: Context, dlgbinding:FragmentCompanyJoinWhereBinding): Dialog(context){

    private lateinit var binding: DialogLocationRecycleBinding


    val obj = object : DialogLocationRecyclerAdapter.OnClickInterface{
        override fun onClick(view: View, position: Int) {
            Log.d("Tester", "onClick: ${locationlist[position]}")
            dlgbinding.txtSi.text = locationlist[position]
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
        binding = DialogLocationRecycleBinding.inflate(layoutInflater)
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
        "서울", "세종", "강원", "인천",
        "경기", "충북", "충남", "경북",
        "대전", "대구", "전북", "경남",
        "울산", "광주", "부산", "전남", "제주"
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

