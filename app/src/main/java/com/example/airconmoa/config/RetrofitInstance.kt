package com.example.airconmoa.config

import com.example.airconmoa.ui.home.HomeRetrofitInterface
import com.example.airconmoa.ui.join_user.JoinRetrofitInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(ApiRepository.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val joinRetrofitInterface: JoinRetrofitInterface =
            retrofit.create(JoinRetrofitInterface::class.java)
        val homeRetrofitInterface: HomeRetrofitInterface =
            retrofit.create(HomeRetrofitInterface::class.java)
    }
}