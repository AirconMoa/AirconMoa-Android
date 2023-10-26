package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityEnterEmailBinding
import com.example.airconmoa_android.databinding.ActivityEnterPasswordBinding

class EnterPasswordActivity : BaseActivityVB<ActivityEnterPasswordBinding>(ActivityEnterPasswordBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enterPasswordBackIv.setOnClickListener {
            finish()
        }

        binding.enterPasswordFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.enterPasswordNextBtn.setOnClickListener {
            val password = binding.enterPasswordSelectedEt.text
            if(password!!.length < 6 || password!!.length > 12) {
                Toast.makeText(this, "비밀번호는 6~12자 이내로 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, EnterPasswordAgainActivity::class.java)
                intent.putExtra("password", password.toString())
                startActivity(intent)
            }
        }
    }
}