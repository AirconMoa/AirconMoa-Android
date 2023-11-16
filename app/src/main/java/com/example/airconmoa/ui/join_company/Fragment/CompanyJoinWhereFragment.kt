package com.example.airconmoa.ui.join_company.Fragment



import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.example.airconmoa.config.BaseFragmentVB

import androidx.fragment.app.FragmentManager
import com.example.airconmoa.ui.join_company.Dialog.LocationDoRecycleDialog
import com.example.airconmoa.ui.join_company.Dialog.LocationRecycleDialog
import com.example.airconmoa.ui.join_company.data.SiData
import com.example.airconmoa.until.getSi
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.FragmentCompanyJoinWhereBinding


class CompanyJoinWhereFragment:
    BaseFragmentVB<FragmentCompanyJoinWhereBinding>(FragmentCompanyJoinWhereBinding::bind, R.layout.fragment_company_join_where) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnLocationSi.setOnClickListener{


                val mydial = LocationRecycleDialog(context!!,this)
                mydial.show()


            }

            btnLocationDo.setOnClickListener {
                if(txtSi.text == "선택"){
                    showCustomToast("지역을 먼저 선택해 주세요.")
                }else{
                showCustomToast(txtSi.text.toString())
                    val mydial = LocationDoRecycleDialog(context!!,this, txtSi.text.toString())
                    mydial.show()
                }


            }





//                dlg.setOnClickedListener(object : LocationRecycleDialog.ButtonClickListener{
//                    override fun onClicked(index: Int?) {
//                        if(index != null){
//                            showCustomToast(index.toString())
//
//                            txtSi.setText(locationlist[index])
//
//                        }
//                    }
//                })
        }
    }



}
