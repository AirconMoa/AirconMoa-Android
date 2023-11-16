package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityEnterEmailBinding


class EnterEmailActivity :
    BaseActivityVB<ActivityEnterEmailBinding>(ActivityEnterEmailBinding::inflate) {

    private var userEmail: String? = null
    private var itemNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterEmailBackIv.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

        binding.enterEmailFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterEmailNextBtn.setOnClickListener {
            showCustomToast("이메일 인증을 진행해주세요")
        }

        binding.loginEmailEt.requestFocus()

        val spinner = binding.enterEmailAddressSpinner

        // Spinner에 사용될 항목들
        val items = listOf("선택", "naver.com", "hanmail.net", "gmail.com", "daum.net", "yahoo.com")

        // 어댑터 생성 및 설정
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 스피너 선택 이벤트 리스너 설정
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
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
                    binding.enterEmailAuthenticateBtn.visibility = View.INVISIBLE
                    binding.enterEmailAuthenticateSelectBtn.visibility = View.VISIBLE
                    binding.enterEmailAtTv.setTextColor(
                        ContextCompat.getColor(
                            this@EnterEmailActivity,
                            R.color.airconmoa_gray
                        )
                    )
                } else {
                    binding.enterEmailAuthenticateBtn.visibility = View.VISIBLE
                    binding.enterEmailAuthenticateSelectBtn.visibility = View.INVISIBLE
                    binding.enterEmailAtTv.setTextColor(
                        ContextCompat.getColor(
                            this@EnterEmailActivity,
                            R.color.airconmoa_gray3
                        )
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.enterEmailAuthenticateBtn.visibility = View.VISIBLE
                binding.enterEmailAuthenticateSelectBtn.visibility = View.INVISIBLE
            }
        }

        binding.enterEmailAuthenticateBtn.setOnClickListener {
            binding.enterEmailErrrorTv.visibility = View.VISIBLE
        }

        binding.enterEmailAuthenticateSelectBtn.setOnClickListener {
            if (binding.loginEmailEt.text!!.isNotEmpty()) {
                val intent = Intent(this, EmailAuthenticationActivity::class.java)
                Log.d("userEmail", userEmail!!)
                val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("userEmail", userEmail)
                editor.apply()
                binding.enterEmailErrrorTv.visibility = View.INVISIBLE
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            } else {
                showCustomToast("이메일을 올바르게 입력해주세요")
                binding.enterEmailErrrorTv.visibility = View.VISIBLE
            }
        }
        setFullScreen()
    }

    override fun onPause() {
        super.onPause()
        val userEmailTextInput = binding.loginEmailEt.text.toString() // 이메일의 앞 부분
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if (userEmailTextInput.isNotEmpty()) {
            editor.putString("userEmailTextInput", userEmailTextInput)
            Log.d("userEmailTextInput", userEmailTextInput)
        }

        if (itemNum != 0) {
            editor.putString("spinnerNumber", itemNum.toString())
            Log.d("spinnerNumber", itemNum.toString())
        }

        editor.apply()
    }


    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val tempEmail = sharedPreferences.getString("userEmailTextInput", null)
        val tempSpinnerNumber = sharedPreferences.getString("spinnerNumber", null)

        if (tempEmail != null) {
            binding.loginEmailEt.setText(tempEmail)
        }

        if (tempSpinnerNumber != null) {
            val spinnerNum = tempSpinnerNumber.toInt()
            binding.enterEmailAddressSpinner.setSelection(spinnerNum)
        }
    }
}