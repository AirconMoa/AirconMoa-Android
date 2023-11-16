package com.example.airconmoa.ui.join_company.Fragment


import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentCompanyJoinWhereBinding
import com.example.airconmoa.ui.join_company.Dialog.LocationDoRecycleDialog
import com.example.airconmoa.ui.join_company.Dialog.LocationRecycleDialog


class CompanyJoinWhereFragment :
    BaseFragmentVB<FragmentCompanyJoinWhereBinding>(
        FragmentCompanyJoinWhereBinding::bind,
        R.layout.fragment_company_join_where
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnLocationSi.setOnClickListener {


                val mydial = LocationRecycleDialog(context!!, this)
                mydial.show()


            }

            btnLocationDo.setOnClickListener {
                if (txtSi.text == "선택") {
                    showCustomToast("지역을 먼저 선택해 주세요.")
                } else {
                    showCustomToast(txtSi.text.toString())
                    val mydial = LocationDoRecycleDialog(context!!, this, txtSi.text.toString())
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
