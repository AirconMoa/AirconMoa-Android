package com.example.airconmoa.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.databinding.FragmentUserHomeInstallBinding
import com.example.airconmoa.ui.estimate_user.UserEstimateActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserHomeInstallFragment : BaseFragmentVB<FragmentUserHomeInstallBinding>(FragmentUserHomeInstallBinding::bind, R.layout.fragment_user_home_install) {

    var companyInfoList = listOf<CompanyItemData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val response = getCompanyInfo()
            if(response.isSuccess) {
                companyInfoList = response.result!!
                Log.d("CompanyInfoList", companyInfoList.toString())

                withContext(Dispatchers.Main) {
                    binding.rvMain.adapter = CompanyItemDataAdapter(companyInfoList)
                    binding.rvMain.layoutManager = LinearLayoutManager(context)
                    val adapter = binding.rvMain.adapter
                    adapter!!.notifyDataSetChanged()
                }
            }
            else {
                val message = response.message
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.estimateWriteBtn.setOnClickListener {
            val intent= Intent(context, UserEstimateActivity::class.java)
            startActivity(intent)
            //findNavController().navigate(R.id.action_homeFragment_to_estimateUserFragment)

        }
    }

    private suspend fun getCompanyInfo(): BaseResponse<List<CompanyItemData>> {
        return RetrofitInstance.homeRetrofitInterface.getCompanyInfo()
    }
}