package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.BuildConfig
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityCreateUserBinding
import com.example.airconmoa.ui.join_user.model.PostOauthLoginRes
import com.example.airconmoa.ui.join_user.model.PostSignUpReq
import com.example.airconmoa.ui.join_user.model.PostUidDeviceTokenReq
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.util.FirebaseAuthUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateUserActivity : JoinActivityInterface, BaseActivityVB<ActivityCreateUserBinding>(ActivityCreateUserBinding::inflate) {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        /** Naver Login Module Initialize */
        val naverClientId = BuildConfig.NAVER_CLIENT_ID
        val naverClientSecret = BuildConfig.NAVER_CLIENT_SECRETE
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, "Aircon Moa")

        binding.createUserBackIv.setOnClickListener {
            finish()
        }

        binding.createUserFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.createUserWithEmailIv.setOnClickListener {
            val intent = Intent(this, AgreementActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        showCustomToast("접근이 거부 됨(동의 취소)")
                    }

                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        showCustomToast("유효하지 않은 앱")
                    }

                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        showCustomToast("인증 수단이 유효하지 않아 인증할 수 없는 상태")
                    }

                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        showCustomToast("요청 파라미터 오류")
                    }

                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        showCustomToast("유효하지 않은 scope ID")
                    }

                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        showCustomToast("설정이 올바르지 않음(android key hash)")
                    }

                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        showCustomToast("서버 내부 에러")
                    }

                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        showCustomToast("앱이 요청 권한이 없음")
                    }

                    else -> { // Unknown
                        showCustomToast("기타 에러")
                    }
                }
            } else if (token != null) {
                Log.d("accessToken", token.accessToken)
                CoroutineScope(Dispatchers.IO).launch {
                    val postSignUpReq = PostSignUpReq(token.accessToken, "kakao")
                    val response = signUp(postSignUpReq)
                    Log.d("Response", response.toString())
                    if (response.isSuccess) {
                        Log.d("CreateUserActivity", response.toString())
                        val signUpResponse : PostOauthLoginRes = response.result!!
                        Log.d("userId", signUpResponse!!.userId.toString())
                        Log.d("accessToken", signUpResponse!!.accessToken)
                        Log.d("userEmail", signUpResponse!!.email)

                        if(FirebaseAuthUtils.getUid() == null) {
                            auth.createUserWithEmailAndPassword(signUpResponse!!.email, "abc123")
                        }

                        FirebaseMessaging.getInstance().token.addOnCompleteListener(
                            OnCompleteListener { task ->
                                if (!task.isSuccessful) {
                                    Log.w(
                                        "MyToken",
                                        "Fetching FCM registration token failed",
                                        task.exception
                                    )
                                    return@OnCompleteListener
                                }
                                val uid = FirebaseAuthUtils.getUid()
                                val deviceToken = task.result

                                CoroutineScope(Dispatchers.IO).launch {
                                    val postUidDeviceTokenReq = PostUidDeviceTokenReq(uid, deviceToken)
                                    val saveRes = saveUidAndToken(
                                        "Bearer " + signUpResponse!!.accessToken,
                                        postUidDeviceTokenReq
                                    )
                                    Log.d("UidToken", saveRes.toString())
                                    if (saveRes.isSuccess) {
                                        Log.d("UidToken", "UID와 디바이스 토큰 저장 완료")
                                    } else {
                                        Log.d("UidToken", "UID와 디바이스 토큰 저장 실패")
                                    }
                                }
                                val intent = Intent(this@CreateUserActivity, MainActivity::class.java)
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                                overridePendingTransition(
                                    R.anim.slide_right_enter,
                                    R.anim.slide_right_exit
                                )
                                finish()
                            })

                    } else {
                        // 로그인 실패 처리
                        Log.d("CreateUserActivity", "로그인 실패")
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@CreateUserActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.createUserWithKakaoIv.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                val token = NaverIdLoginSDK.getAccessToken().toString()
                Log.d("naverToken", token)
//               if(token != null) {
//                   CoroutineScope(Dispatchers.IO).launch {
//                       val response = naverCallback(token)
//                       Log.d("IntroActivity", response.toString())
//                       if (response.isSuccess) {
//                           Log.d("email", response.result!!.email)
//                           Log.d("uid", FirebaseAuthUtils.getUid())
//                           if(FirebaseAuthUtils.getUid() == null) {
//                               auth.createUserWithEmailAndPassword(response.result!!.email, "abc123")
//                           }
//                           FirebaseMessaging.getInstance().token.addOnCompleteListener(
//                               OnCompleteListener { task ->
//                                   if (!task.isSuccessful) {
//                                       Log.w("MyToken", "Fetching FCM registration token failed", task.exception)
//                                       return@OnCompleteListener
//                                   }
//                                   val uid = FirebaseAuthUtils.getUid()
//                                   val deviceToken = task.result
//                                   val userInfo = UserInfo(uid, response.result?.userId,
//                                       deviceToken, response.result?.accessToken, response.result?.refreshToken)
//                                   Log.d("userInfo", userInfo.toString())
//                                   FirebaseRef.userInfo.child(uid).setValue(userInfo)

//                                   CoroutineScope(Dispatchers.IO).launch {
//                                       val postNaverUserReq = PostNaverUserReq(uid, deviceToken)
//                                       val saveRes = saveNaverUidAndToken(response.result?.accessToken!!, postNaverUserReq)
//                                       Log.d("UidToken", saveRes.toString())
//                                       if (saveRes.isSuccess) {
//                                           Log.d("UidToken", "UID와 디바이스 토큰 저장 완료")
//                                       } else {
//                                           Log.d("UidToken", "UID와 디바이스 토큰 저장 실패")
//                                       }
//                                   }
//                                   val intent = Intent(this@CreateUserActivity, MainActivity::class.java)
//                                   startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                                   finish()
//                               })
//                           Log.d("IntroActivity", "로그인 완료")
//                       } else {
//                           // 로그인 실패 처리
//                           Log.d("IntroActivity", "로그인 실패")
//                           val message = response.message
//                           Log.d("IntroActivity", message)
//                           withContext(Dispatchers.Main) {
//                               Toast.makeText(this@CreateUserActivity, message, Toast.LENGTH_SHORT).show()
//                           }
//                       }
//                   }
//               }

//               else {
//                   Toast.makeText(this@CreateUserActivity, "접근이 거부 됨", Toast.LENGTH_SHORT).show()
//               }
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
        setFullScreen()
    }

    private suspend fun signUp(postSignUpReq: PostSignUpReq): BaseResponse<PostOauthLoginRes> {
        return RetrofitInstance.joinRetrofitInterface.signUp(postSignUpReq)
    }

    private suspend fun saveUidAndToken(accessToken: String, postUidDeviceTokenReq: PostUidDeviceTokenReq): BaseResponse<String> {
        return RetrofitInstance.joinRetrofitInterface.saveUidAndToken(accessToken, postUidDeviceTokenReq)
    }

    override fun onJoinSuccess() {
        TODO("Not yet implemented")
    }

    override fun onJoinFailure() {
        TODO("Not yet implemented")
    }
}


