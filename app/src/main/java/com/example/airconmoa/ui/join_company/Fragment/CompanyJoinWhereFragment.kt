package com.example.airconmoa.ui.join_company.Fragment



import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.ui.join_company.data.SiData
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.FragmentCompanyJoinWhereBinding


class CompanyJoinWhereFragment:
    BaseFragmentVB<FragmentCompanyJoinWhereBinding>(FragmentCompanyJoinWhereBinding::bind, R.layout.fragment_company_join_where), View.OnClickListener  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        with(binding){

            btnLocationSi.setOnClickListener{

                val dialogView = layoutInflater.inflate(R.layout.dialog_location_si,null)

                val builder = AlertDialog.Builder(context!!)
                    .setView(dialogView)
                    .create()

                builder.window?.setGravity(Gravity.BOTTOM)
                builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))




                builder.show()



            }


        }


    }

    override fun onClick(v: View?) {

        showCustomToast(v?.id.toString())


    }


}
