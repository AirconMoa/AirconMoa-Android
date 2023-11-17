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
/*class EstimateCompanyFragment : BaseFragmentVB<FragmentEstimateCompanyBinding>(
    FragmentEstimateCompanyBinding::bind,
    R.layout.fragment_estimate_company
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList: ArrayList<EstimateItemData> = arrayListOf()
        dataList.apply {
            add(
                EstimateItemData(
                    "모아",
                    "2023.09.26",
                    "신규설치 · 신규건물",
                    "500m",
                    R.drawable.detail_review_user_profile
                )

            )
            add(
                EstimateItemData(
                    "모아2",
                    "2023.09.27",
                    "신규설치 · 기존건물",
                    "510m",
                    R.drawable.detail_review_user_profile
                )
            )
            add(
                EstimateItemData(
                    "모아3",
                    "2023.10.01",
                    "신규설치 · 기존건물",
                    "450m",
                    R.drawable.detail_review_user_profile
                )
            )
            add(
                EstimateItemData(
                    "모아4",
                    "2023.10.03",
                    "교체",
                    "650m",
                    R.drawable.detail_review_user_profile
                )
            )
            add(
                EstimateItemData(
                    "모아5",
                    "2023.10.04",
                    "신규설치 · 기존건물",
                    "650m",
                    R.drawable.detail_review_user_profile
                )
            )
        }

        binding.rvMainCompany.adapter =
            EstimateItemDataAdapter(dataList, this@EstimateCompanyFragment)
        binding.rvMainCompany.layoutManager = LinearLayoutManager(context)
>>>>>>> feature/fix

 */
    }
}