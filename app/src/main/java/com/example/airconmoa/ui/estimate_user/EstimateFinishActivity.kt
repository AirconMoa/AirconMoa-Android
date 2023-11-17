package com.example.airconmoa.ui.estimate_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.databinding.ActivityEstimateFinishBinding
import com.example.airconmoa.ui.estimate_user.model.RequestEstimateItemData
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.util.Constants
import com.navercorp.nid.oauth.NidOAuthPreferencesManager.accessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class EstimateFinishActivity :
    BaseActivityVB<ActivityEstimateFinishBinding>(ActivityEstimateFinishBinding::inflate) {

    override fun onDestroy() {
        super.onDestroy()
        Log.d("accessToken",accessToken.toString())

    }

    override fun onPause() {
        super.onPause()
        Log.d("accessToken",accessToken.toString())

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("airconmoa", AppCompatActivity.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString(Constants.X_ACCESS_TOKEN, null)
        //val accessToken="eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6ImdtbHN0anExMjNAbmF2ZXIuY29tIiwiaWF0IjoxNzAwMjMwMTQ2LCJleHAiOjE3MDAyNTE3NDZ9.q4Y6Axm7co1NopozZl6go-1bfpn5PQwp6Vf4MZIjhRxHfLJoyeFeppx4bQWByGdujuhufyHAPv93yGIAM-LlYQ"
        Log.d("api통신",accessToken.toString())
        with(binding) {

            var mapOfApi1 = mapOf(
                "에어컨 신규 설치 / 신규 건물" to "NEW_BUILDING",
                "에어컨 신규 설치 / 기존 건물" to "EXISTING_BUILDING", "에어컨 교체" to "AIRCON_REPLACEMENT"
            )!!

            var mapOfApi2 = mapOf(
                "아파트" to "APARTMENT", "주택" to "HOUSING",
                "빌라" to "VILLA", "빌딩" to "BUILDING", "기타" to "OTHERS"
            )!!

            var mapOfApi3 =
                mapOf("삼성" to "SAMSUNG", "기타" to "OTHERS", "캐리어" to "CARRIER", "LG" to "LG")!!

            var info1 = intent!!.getStringExtra("info1").toString()
            var info2 = intent!!.getStringExtra("info2").toString()
            var info3 = intent!!.getStringExtra("info3").toString()
            var info4 = intent!!.getIntExtra("info4", 0)
            var info5 = intent!!.getStringExtra("info5").toString()
            var info6 = intent!!.getStringExtra("info6").toString()

            installContentTv.text = info1
            installAddressContentTv.text = info2
            installSiteContentTv.text = info3
            installNumContentTv.text = info4.toString() + "대"
            installDateContentTv.text = info5
            brandContentTv.text = info6

            backBtn.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val REID = RequestEstimateItemData(
                            mapOfApi1[info1], info2, mapOfApi2[info3]!!,

                            info4, info5, mapOfApi3[info6]!!
                        )
                        Log.d("api통신", REID.toString())
                        val response = postWriteEstimate(accessToken!!, REID)
                        Log.d("api통신", response.toString())
                        if (response.isSuccess) {
                            Log.d("api통신", "성공")
                            withContext(Dispatchers.Main) {
                                Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()

                                val intent= Intent(this@EstimateFinishActivity,MainActivity::class.java)
                                startActivity(intent);
                                finish()
                            }
                        } else {
                            Log.d("api통신", response.message)
                            withContext(Dispatchers.Main) {
                                Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()

                                val intent= Intent(this@EstimateFinishActivity,MainActivity::class.java)
                                startActivity(intent);
                                finish()
                            }
                        }
                    } catch (e: HttpException) {
                        Log.d("api통신", "500 "+e.message)


                    }
                    catch (e:Exception){
                        Log.d("api통신", "이외 에러 "+e.message)

                    }
                }
            }
        }
    }
    private suspend fun postWriteEstimate(        accessToken: String,
                                                  requestEstimateItemData: RequestEstimateItemData): BaseResponse<String> {
        return RetrofitInstance.estimateWriteUserRetrofitInterface.postWriteEstimate(accessToken,requestEstimateItemData)
    }
}