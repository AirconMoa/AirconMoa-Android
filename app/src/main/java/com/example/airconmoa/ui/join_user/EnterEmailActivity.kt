package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityAgreementBinding
import com.example.airconmoa_android.databinding.ActivityEnterEmailBinding

class EnterEmailActivity : BaseActivityVB<ActivityEnterEmailBinding>(ActivityEnterEmailBinding::inflate) {

    lateinit var userEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterEmailBackIv.setOnClickListener {
            finish()
        }

        binding.enterEmailFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val spinner = binding.enterEmailAddressSpinner

        // Spinner에 사용될 항목들
        val items = listOf("선택", "naver.com", "hanmail.net", "gmail.com", "daum.net", "yahoo.com")

        // 어댑터 생성 및 설정
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 스피너 선택 이벤트 리스너 설정
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                userEmail = if(position == 0) {
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

                if(position != 0) {
                    binding.enterEmailAuthenticateBtn.visibility = View.INVISIBLE
                    binding.enterEmailAuthenticateSelectBtn.visibility = View.VISIBLE
                }
                else {
                    binding.enterEmailAuthenticateBtn.visibility = View.VISIBLE
                    binding.enterEmailAuthenticateSelectBtn.visibility = View.INVISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.enterEmailAuthenticateBtn.visibility = View.VISIBLE
                binding.enterEmailAuthenticateSelectBtn.visibility = View.INVISIBLE
            }
        }

        binding.enterEmailAuthenticateBtn.setOnClickListener {
            Toast.makeText(this, "이메일을 올바르게 입력해주세요", Toast.LENGTH_SHORT).show()
        }

        binding.enterEmailAuthenticateSelectBtn.setOnClickListener {
            if(binding.loginEmailEt.text!!.isNotEmpty()) {
                val intent = Intent(this, EmailAuthenticationActivity::class.java)
                intent.putExtra("userEmail", userEmail)
                Log.d("userEmail", userEmail)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "이메일을 올바르게 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}