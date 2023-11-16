package com.example.airconmoa.ui.join_user


//class JoinService (val view : JoinActivityInterface){
//
//    fun join(postSignUpReq : PostSignUpReq) {
//        RetrofitInstance.joinRetrofitInterface.signUp(postSignUpReq).enqueue(object: Callback<BaseResponse<PostOauthLoginRes>> {
//            override fun onResponse(call: Call<BaseResponse<PostOauthLoginRes>>, response: Response<BaseResponse<PostOauthLoginRes>>) {
//                val response = response.body()!!
//                when(response.code) {
//                    1000 -> view.onJoinSuccess()
//                    else -> view.onJoinFailure(response.message)
//                }
//            }
//            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
//                Log.d("Join-Failure", t.message.toString())
//            }
//        })
//        Log.d("SignUpActivity", "All Finished")
//    }
//}