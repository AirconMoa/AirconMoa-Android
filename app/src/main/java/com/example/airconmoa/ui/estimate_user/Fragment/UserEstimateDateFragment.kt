package com.example.airconmoa.ui.estimate_user.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEstimateDateBinding
import com.example.airconmoa.ui.main_user.MainActivity
import java.util.Calendar


class UserEstimateDateFragment : BaseFragmentVB<FragmentUserEstimateDateBinding>(
    FragmentUserEstimateDateBinding::bind, R.layout.fragment_user_estimate_date
) {


    private var calMonth = Calendar.MONTH+1
    private var calDay = Calendar.DAY_OF_MONTH
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var info1=arguments?.getString("info1").toString()
        var info2=arguments?.getString("info2").toString()
        var info3=arguments?.getString("info3").toString()
        var info4=arguments?.getInt("info4")

        Log.d("info1", "D "+info1)
        Log.d("info1", "D "+info2)
        Log.d("info1", "D "+info3)
        Log.d("info1", "D "+info4.toString())

        with(binding) {
            btnNext.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("info1", info1)
                bundle.putString("info2",info2)
                bundle.putString("info3",info3)
                bundle.putInt("info4",info4!!)
                bundle.putString("info5","2023-"+calMonth.toString()+"-"+calDay.toString())
                findNavController().navigate(R.id.action_estimateDateFragment_to_estimateBrandFragment,bundle)

            }
            closeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
                while (findNavController().popBackStack());
            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_estimateDateFragment_to_estimateQuantityFragment)

            }

            calView.setOnDateChangeListener { calView, year, month, dayOfMonth ->
                calMonth = month + 1
                calDay = dayOfMonth
            }
        }
    }
}