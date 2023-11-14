package com.example.airconmoa.ui.login_user

import android.Manifest
import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.ui.login_user.model.LoginResponseData
import com.example.airconmoa.ui.main_user.MainActivity
import com.example.airconmoa.util.Constants
import com.example.airconmoa.R.*
import com.example.airconmoa.config.BaseResponse
import com.example.airconmoa.config.RetrofitInstance
import com.example.airconmoa.databinding.ActivityLoginBinding
import com.example.airconmoa.ui.join_user.model.PostOauthLoginRes
import com.example.airconmoa.ui.join_user.model.PostSignUpReq
import com.example.airconmoa.ui.join_user.model.PostUidDeviceTokenReq
import com.example.airconmoa.ui.join_user.NewMemberActivity
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


class LoginActivity : BaseActivityVB<ActivityLoginBinding>(ActivityLoginBinding::inflate){

    private var userEmail : String? = null
    private var itemNum = 0
    private lateinit var auth: FirebaseAuth

    private var social = ""
    private lateinit var neededPermissionList : ArrayList<String>
    private val requiredPermissionList =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        // Android 13 이상에서 필요한 권한 목록과 Android 13 미만에서 필요한 권한 목록이 서로 다르기 때문에 분기처리한다.
            arrayOf(  // 안드로이드 13 이상 필요한 권한들
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            )
        }
        else {
            arrayOf(  // 안드로이드 13 미만 필요한 권한들
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            )
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        onCheckPermissions()

        auth = Firebase.auth

        binding.loginBackIv.setOnClickListener {
            finish()
            overridePendingTransition(com.example.airconmoa.R.anim.slide_left_enter, com.example.airconmoa.R.anim.slide_left_exit)
        }

        binding.loginFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        /** Naver Login Module Initialize */
        val naverClientId = getString(string.social_login_info_naver_client_id)
        val naverClientSecret = getString(string.social_login_info_naver_client_secret)
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, "Aircon Moa")
//       binding.btnKakaoLogin.setOnClickListener {
//            social = "KAKAO"
//            showLoading()
//            kakaoLogin()
//        }
//
//        binding.btnNaverLogin.setOnClickListener {
//            social = "NAVER"
//            showLoading()
//            naverLogin()
//        }

        val spinner = binding.loginEmailAddressSpinner

        // Spinner에 사용될 항목들
        val items = listOf("선택", "naver.com", "hanmail.net", "gmail.com", "daum.net", "yahoo.com")

        // 어댑터 생성 및 설정
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 스피너 선택 이벤트 리스너 설정
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                itemNum = position
                userEmail = if (position == 0) {
                    binding.loginEmailEt.text.toString() + ""
                } else if (position == 1) {
                    binding.loginEmailEt.text.toString() + "@naver.com"
                } else if (position == 2) {
                    binding.loginEmailEt.text.toString() + "@hanmail.net"
                } else if (position == 3) {
                    binding.loginEmailEt.text.toString() + "@gmail.com"
                } else if (position == 4) {
                    binding.loginEmailEt.text.toString() + "@daum.net"
                } else {
                    binding.loginEmailEt.text.toString() + "@yahoo.com"
                }

                if (position != 0) {
                    binding.loginAtTv.setTextColor(
                        ContextCompat.getColor(
                            this@LoginActivity,
                            color.airconmoa_gray
                        )
                    )
                } else {
                    binding.loginAtTv.setTextColor(
                        ContextCompat.getColor(
                            this@LoginActivity,
                            color.airconmoa_gray3
                        )
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.loginNextBtn.setOnClickListener {
            Log.d("userEmail", userEmail.toString())
            val password = binding.loginSelectedEt.text.toString()
            if (userEmail == null || userEmail == "" || binding.loginEmailEt.text.toString() == "" || itemNum == 0) {
                binding.loginEmailErrorTv.visibility = View.VISIBLE
                showCustomToast("이메일을 올바르게 입력해주세요")
            } else if (password.length < 6 || password.length > 12) {
                binding.loginPasswordErrorTv.visibility = View.VISIBLE
                showCustomToast("비밀번호는 6~12자 이내로 입력해주세요")
            } else {
                auth.signInWithEmailAndPassword(userEmail.toString(), password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
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
                                    val deviceToken = task.result
                                    Log.d("deviceToken", deviceToken)
                                    // val userInfo = UserInfo(uid, deviceToken)
                                    // Log.d("userInfo", userInfo.toString())

                                    // FirebaseRef.userInfo.child(uid).setValue(userInfo)
                                    Log.d("LoginActivity", "로그인 완료")
                                    binding.loginEmailErrorTv.visibility = View.INVISIBLE
                                    binding.loginPasswordErrorTv.visibility = View.INVISIBLE
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    overridePendingTransition(
                                        anim.slide_right_enter,
                                        anim.slide_right_exit
                                    )
                                })
                        } else {
                            Log.d("LoginActivity", "로그인 실패")
                            showCustomToast("비밀번호가 일치하지 않습니다")
                        }
                    }

            }
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
                    Log.d("CreateUserActivity", response.toString())
                    if (response.isSuccess) {
                        val signUpResponse : PostOauthLoginRes = response.result!!
                        Log.d("userId", signUpResponse!!.userId.toString())
                        Log.d("accessToken", signUpResponse!!.accessToken)
                        Log.d("userEmail", signUpResponse!!.email)
                        val sharedPreferences = getSharedPreferences("airconmoa", MODE_PRIVATE)
                        sharedPreferences.edit()
                            .putString(Constants.X_ACCESS_TOKEN, "Bearer " + signUpResponse!!.accessToken)
                            .apply()

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
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                                overridePendingTransition(
                                    anim.slide_right_enter,
                                    anim.slide_right_exit
                                )
                                finish()
                            })

                    } else {
                        // 로그인 실패 처리
                        Log.d("CreateUserActivity", "로그인 실패")
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.loginWithKakaoIv.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) { // 카카오톡 설치 확인
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback) // 카카오톡 로그인
            } else {
                UserApiClient.instance.loginWithKakaoAccount(
                    this,
                    callback = callback
                ) // 카카오 이메일 로그인
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

        binding.loginWithNaverOAuth.setOAuthLogin(oauthLoginCallback = oauthLoginCallback)

        binding.loginWithNaverIv.setOnClickListener {
            binding.loginWithNaverOAuth.performClick()
        }
    }

    private fun onCheckPermissions(){
        neededPermissionList = arrayListOf<String>()

        requiredPermissionList.forEach{permission->
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) neededPermissionList.add(permission)
        }

        if(neededPermissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this, neededPermissionList.toArray(arrayOf<String>()), Constants.RC_PERMISSION)
        }
    }

    private suspend fun signUp(postSignUpReq: PostSignUpReq): BaseResponse<PostOauthLoginRes> {
        return RetrofitInstance.joinRetrofitInterface.signUp(postSignUpReq)
    }

    private suspend fun saveUidAndToken(accessToken: String, postUidDeviceTokenReq: PostUidDeviceTokenReq): BaseResponse<String> {
        return RetrofitInstance.joinRetrofitInterface.saveUidAndToken(accessToken, postUidDeviceTokenReq)
    }

    private fun storeTokens(result : LoginResponseData){
//        val sharedPreferences = getSharedPreferences("airconmoa", MODE_PRIVATE)
//        sharedPreferences.edit()
//            .putString(Constants.X_ACCESS_TOKEN, "Bearer " + result.accessToken)
//            .putString(Constants.X_REFRESH_TOKEN, result.refreshToken)
//            .putString(Constants.X_ACCESS_EXPIRE, result.accessTokenExpiredDate)
//            .putString(Constants.X_REFRESH_EXPIRE, result.refreshTokenExpiredDate)
//            .putString(Constants.X_LOGIN_TYPE, social)
//            .apply()
    }

//    override fun onPostLoginSuccess(result : LoginResponseData) {
//        // 존재하는 유저. 로그인
//        // dismissLoading()
//
//        // accessToken/refreshToken 저장
//        storeTokens(result)
//
//        // MainActivity로 이동
//        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//        startActivity(intent)
//    }

//    override fun onPostLoginFailure(message : String, uuid : String) {
//        Log.d("LoginActivity","$uuid")
//        // dismissLoading()
//        if(uuid.isNotBlank()){
//            // 존재하지 않는 유저. 회원가입
//            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
//                .putExtra("authType",social)
//                .putExtra("authId",uuid)
//            startActivity(intent)
//        }
//    }
}