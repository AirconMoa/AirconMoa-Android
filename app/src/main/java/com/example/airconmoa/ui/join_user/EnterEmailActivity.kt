package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityAgreementBinding
import com.example.airconmoa_android.databinding.ActivityEnterEmailBinding

class EnterEmailActivity : BaseActivityVB<ActivityEnterEmailBinding>(ActivityEnterEmailBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterEmailBackIv.setOnClickListener {
            finish()
        }

        binding.enterEmailFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }

        val spinner = findViewById<Spinner>(R.id.enter_email_address_spinner)

        // Spinner에 사용될 항목들
        val items = listOf("선택", "naver.com", "hanmail.net", "gmail.com", "daum.net", "yahoo.com")

        // 어댑터 생성 및 설정
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}