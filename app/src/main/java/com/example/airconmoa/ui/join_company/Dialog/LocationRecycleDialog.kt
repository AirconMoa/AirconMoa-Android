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
import androidx.recyclerview.widget.RecyclerView
import com.example.airconmoa.ui.join_company.Adapter.DialogLocationRecyclerAdapter
import com.example.airconmoa.ui.login_user.model.LoginPostData
import com.example.airconmoa_android.databinding.DialogLocationRecycleBinding



class LocationRecycleDialog(private val context:AppCompatActivity){

    private lateinit var binding: DialogLocationRecycleBinding
    private val dlg = Dialog(context)

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


}

