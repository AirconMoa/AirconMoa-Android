package com.example.airconmoa.ui.estimate_company

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.App
import com.example.airconmoa.config.App.Companion.sharedPreferences
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.databinding.FragmentEstimateWriteCompanyBinding
import com.example.airconmoa.ui.estimate_company.model.GetRequestEstimateRes
import com.example.airconmoa.ui.estimate_company.model.PostRequestEstimateReq
import com.example.airconmoa.ui.join_user.model.PostUidDeviceTokenReq
import com.example.airconmoa.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class EstimateWriteCompanyFragment : BaseFragmentVB<FragmentEstimateWriteCompanyBinding>(FragmentEstimateWriteCompanyBinding::bind, R.layout.fragment_estimate_write_company) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.finishBtn.setOnClickListener {
            val intent= Intent(context,WriteCompleteActivity::class.java)
            startActivity(intent)
            while(findNavController().popBackStack());
        }
        val sharedPreferences = activity!!.getSharedPreferences("airconmoa", AppCompatActivity.MODE_PRIVATE)
        val requestEstimateId = arguments?.getLong("requestEstimateId")
        val accessToken = sharedPreferences.getString(Constants.X_ACCESS_TOKEN, null)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRequestEstimate(accessToken!!, requestEstimateId!!)
                Log.d("response", response.toString())

                withContext(Dispatchers.Main) {
                    if (response.isSuccess) {
                        val result = response.result
                        binding.nickNameContentTv.text = arguments?.getString("nickname")
                        when (result?.installInfo) {
                            "NEW_BUILDING" -> binding.setInfoContentTv.text = "신규 설치 ∙ 신규 건물"
                            "EXISTING_BUILDING" -> binding.setInfoContentTv.text =  "신규 설치 ∙ 기존 건물"
                            "AIRCON_REPLACEMENT" -> binding.setInfoContentTv.text =  "에어컨 교체"
                            else -> binding.setInfoContentTv.text =  "기타 설치 정보"
                        }
                        binding.setAddressContentTv.text = result?.installAddress
                        when (result?.buildingType) {
                            "APARTMENT" -> binding.setSiteTypeContentTv.text = "아파트"
                            "HOUSING" -> binding.setSiteTypeContentTv.text = "주택"
                            "VILLA" -> binding.setSiteTypeContentTv.text = "빌라"
                            "BUILDING" -> binding.setSiteTypeContentTv.text = "빌딩"
                            else -> binding.setSiteTypeContentTv.text = "기타"
                        }
                        binding.setNumContentTv.text = result?.amount.toString()

                        val installDate = result?.installationDate // yyyy-mm-dd
                        val parsedDate = LocalDate.parse(installDate)
                        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MM월 dd일"))
                        binding.setDateContentTv.text = formattedDate

                        when (result?.brand) {
                            "LG" ->  binding.brandContentTv.text = "LG"
                            "SAMSUNG" ->  binding.brandContentTv.text = "삼성"
                            "CARRIER" ->  binding.brandContentTv.text = "Carrier"
                            else ->  binding.brandContentTv.text = "기타"
                        }

                    } else {
                        val message = response.message
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "Exception: ${e.message}")
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRequestEstimateDate(requestEstimateId!!)
                Log.d("requestDate", response.toString())

                withContext(Dispatchers.Main) {
                    if (response.isSuccess) {
                        val requestDate = response.result // yyyy-mm-dd
                        val parsedDate = LocalDate.parse(requestDate)
                        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
                        binding.estimateRequestDateContentTv.text = formattedDate
                    } else {
                        val message = response.message
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "Exception: ${e.message}")
            }
        }
    }

    private suspend fun getRequestEstimate(accessToken : String, requestEstimateId : Long): BaseResponse<PostRequestEstimateReq> {
        return RetrofitInstance.estimateCompanyRetrofitInterface.getRequestEstimate(accessToken, requestEstimateId)
    }

    private suspend fun getRequestEstimateDate(requestEstimateId : Long): BaseResponse<String> {
        return RetrofitInstance.estimateCompanyRetrofitInterface.getRequestEstimateDate(requestEstimateId)
    }

}