package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityCreateUserBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback

class CreateUserActivity : BaseActivityVB<ActivityCreateUserBinding>(ActivityCreateUserBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Naver Login Module Initialize */
        val naverClientId = getString(R.string.social_login_info_naver_client_id)
        val naverClientSecret = getString(R.string.social_login_info_naver_client_secret)
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, "Aircon Moa")

        binding.createUserBackIv.setOnClickListener {
            finish()
        }

        binding.createUserFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.createUserWithEmailIv.setOnClickListener {
            val intent = Intent(this, AgreementActivity::class.java)
            startActivity(intent)
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
//            else if (token != null) {
//                Log.d("accessToken", token.accessToken)
//                CoroutineScope(Dispatchers.IO).launch {
//                    val response = kakaoCallback(token.accessToken)
//                    Log.d("IntroActivity", response.toString())
//                    if (response.isSuccess) {
//                        Log.d("email", response.result!!.email)
//                        if(FirebaseAuthUtils.getUid() == null) {
//                            auth.createUserWithEmailAndPassword(response.result!!.email, "abc123")
//                        }
//                        FirebaseMessaging.getInstance().token.addOnCompleteListener(
//                            OnCompleteListener { task ->
//                                if (!task.isSuccessful) {
//                                    Log.w("MyToken", "Fetching FCM registration token failed", task.exception)
//                                    return@OnCompleteListener
//                                }
//                                val uid = FirebaseAuthUtils.getUid()
//                                val deviceToken = task.result
//                                val userInfo = UserInfo(uid, response.result?.userId,
//                                    deviceToken, response.result?.accessToken, response.result?.refreshToken)
//                                Log.d("userInfo", userInfo.toString())
//                                FirebaseRef.userInfo.child(uid).setValue(userInfo)
//
//                                CoroutineScope(Dispatchers.IO).launch {
//                                    val postKakaoUserReq = PostKakaoUserReq(uid, deviceToken)
//                                    val saveRes = saveUidAndToken(response.result?.accessToken!!, postKakaoUserReq)
//                                    Log.d("UidToken", saveRes.toString())
//                                    if (saveRes.isSuccess) {
//                                        Log.d("UidToken", "UID와 디바이스 토큰 저장 완료")
//                                    } else {
//                                        Log.d("UidToken", "UID와 디바이스 토큰 저장 실패")
//                                    }
//                                }
//                                val intent = Intent(this@CreateUserActivity, MainActivity::class.java)
//                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                                finish()
//                            })
//                        Log.d("IntroActivity", "로그인 완료")
//                    } else {
//                        // 로그인 실패 처리
//                        Log.d("IntroActivity", "로그인 실패")
//                        val message = response.message
//                        Log.d("IntroActivity", message)
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(this@CreateUserActivity, message, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
        }

        binding.createUserWithKakaoIv.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                val token = NaverIdLoginSDK.getAccessToken().toString()
                Log.d("naverToken", token)
//                if(token != null) {
//                    CoroutineScope(Dispatchers.IO).launch {
//                        val response = naverCallback(token)
//                        Log.d("IntroActivity", response.toString())
//                        if (response.isSuccess) {
//                            Log.d("email", response.result!!.email)
//                            Log.d("uid", FirebaseAuthUtils.getUid())
//                            if(FirebaseAuthUtils.getUid() == null) {
//                                auth.createUserWithEmailAndPassword(response.result!!.email, "abc123")
//                            }
//                            FirebaseMessaging.getInstance().token.addOnCompleteListener(
//                                OnCompleteListener { task ->
//                                    if (!task.isSuccessful) {
//                                        Log.w("MyToken", "Fetching FCM registration token failed", task.exception)
//                                        return@OnCompleteListener
//                                    }
//                                    val uid = FirebaseAuthUtils.getUid()
//                                    val deviceToken = task.result
//                                    val userInfo = UserInfo(uid, response.result?.userId,
//                                        deviceToken, response.result?.accessToken, response.result?.refreshToken)
//                                    Log.d("userInfo", userInfo.toString())
//                                    FirebaseRef.userInfo.child(uid).setValue(userInfo)
//
//                                    CoroutineScope(Dispatchers.IO).launch {
//                                        val postNaverUserReq = PostNaverUserReq(uid, deviceToken)
//                                        val saveRes = saveNaverUidAndToken(response.result?.accessToken!!, postNaverUserReq)
//                                        Log.d("UidToken", saveRes.toString())
//                                        if (saveRes.isSuccess) {
//                                            Log.d("UidToken", "UID와 디바이스 토큰 저장 완료")
//                                        } else {
//                                            Log.d("UidToken", "UID와 디바이스 토큰 저장 실패")
//                                        }
//                                    }
//                                    val intent = Intent(this@CreateUserActivity, MainActivity::class.java)
//                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                                    finish()
//                                })
//                            Log.d("IntroActivity", "로그인 완료")
//                        } else {
//                            // 로그인 실패 처리
//                            Log.d("IntroActivity", "로그인 실패")
//                            val message = response.message
//                            Log.d("IntroActivity", message)
//                            withContext(Dispatchers.Main) {
//                                Toast.makeText(this@CreateUserActivity, message, Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
//                }
//
//                else {
//                    Toast.makeText(this@CreateUserActivity, "접근이 거부 됨", Toast.LENGTH_SHORT).show()
//                }
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                Log.d("naverToken", errorCode)
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.d("naverToken", errorDescription.toString())
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        binding.createUserWithNaverOAuth.setOAuthLogin(oauthLoginCallback = oauthLoginCallback)

        binding.createUserWithNaverIv.setOnClickListener {
            binding.createUserWithNaverOAuth.performClick()
        }
    }

//    private suspend fun kakaoCallback(accessToken: String): BaseResponse<PostKakaoLoginRes> {
//        return RetrofitInstance.kakaoApi.kakaoCallback(accessToken)
//    }
//
//    private suspend fun saveUidAndToken(accessToken: String, postKakaoUserReq: PostKakaoUserReq): BaseResponse<String> {
//        return RetrofitInstance.kakaoApi.saveUidAndToken(accessToken, postKakaoUserReq)
//    }

}