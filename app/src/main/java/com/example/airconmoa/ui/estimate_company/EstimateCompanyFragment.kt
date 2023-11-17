package com.example.airconmoa.ui.estimate_company

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.databinding.FragmentEstimateCompanyBinding
import com.example.airconmoa.ui.estimate_company.model.EstimateItemData
import com.example.airconmoa.ui.estimate_company.model.GetRequestEstimateRes
import com.example.airconmoa.ui.home.CompanyItemData
import com.example.airconmoa.ui.home.CompanyItemDataAdapter
import com.example.airconmoa.ui.main_company.MainCompanyActivity
import com.example.airconmoa.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EstimateCompanyFragment : BaseFragmentVB<FragmentEstimateCompanyBinding>(FragmentEstimateCompanyBinding::bind, R.layout.fragment_estimate_company) {

    var requestEstimateList = listOf<GetRequestEstimateRes>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity!!.getSharedPreferences("airconmoa", AppCompatActivity.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString(Constants.X_ACCESS_TOKEN, null)

        CoroutineScope(Dispatchers.IO).launch {
            val response = getRequestEstimateList(accessToken!!)
            if(response.isSuccess) {
                requestEstimateList = response.result!!
                Log.d("requestEstimateList", requestEstimateList.toString())

                withContext(Dispatchers.Main) {
                    val estimateItemDataAdapter = EstimateItemDataAdapter(requestEstimateList, this@EstimateCompanyFragment)
                    binding.rvMainCompany.adapter = estimateItemDataAdapter
                    binding.rvMainCompany.layoutManager = LinearLayoutManager(context)
                    val adapter = binding.rvMainCompany.adapter
                    adapter!!.notifyDataSetChanged()
                    estimateItemDataAdapter.setItemClickListener(object: EstimateItemDataAdapter.OnItemClickListener {
                        override fun onItemClick(getRequestEstimateRes: GetRequestEstimateRes) {
                            changeToEstimateWriteCompanyFragment(getRequestEstimateRes.requestEstimateId, getRequestEstimateRes.userNickname)
                        }

                    })
                }
            }
            else {
                val message = response.message
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun changeToEstimateWriteCompanyFragment(requestEstimateId: Long, nickname : String) {
        (context as MainCompanyActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.frame, EstimateWriteCompanyFragment().apply {
                arguments = Bundle().apply {
                    putLong("requestEstimateId", requestEstimateId)
                    putString("nickname", nickname )
                }
            })
            .commitAllowingStateLoss()
    }
    private suspend fun getRequestEstimateList(accessToken : String): BaseResponse<List<GetRequestEstimateRes>> {
        return RetrofitInstance.estimateCompanyRetrofitInterface.getRequestEstimateList(accessToken)
    }
}