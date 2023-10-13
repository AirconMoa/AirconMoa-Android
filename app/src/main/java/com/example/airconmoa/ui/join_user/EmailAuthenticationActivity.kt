package com.example.airconmoa.ui.join_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.R
import com.example.airconmoa_android.databinding.ActivityCreateUserBinding
import com.example.airconmoa_android.databinding.ActivityEmailAuthenticationBinding

class EmailAuthenticationActivity : BaseActivityVB<ActivityEmailAuthenticationBinding>(ActivityEmailAuthenticationBinding::inflate) {


    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var editText6: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEditTextFocusChangeListener()

        editText1 = binding.emailAuthenticationEt1
        editText2 = binding.emailAuthenticationEt2
        editText3 = binding.emailAuthenticationEt3
        editText4 = binding.emailAuthenticationEt4
        editText5 = binding.emailAuthenticationEt5
        editText6 = binding.emailAuthenticationEt6
        setEditTextFocusChangeListener()

        binding.emailAuthenticationBackIv.setOnClickListener {
            finish()
        }

        binding.emailAuthenticationFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            startActivity(intent)
        }

        timerTextView = binding.emailAuthenticationTimerTv

        // 3분 동안의 타이머 생성
        countDownTimer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 타이머가 갱신될 때마다 TextView에 남은 시간을 표시
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                val timeLeft = String.format("%d:%02d", minutes, seconds)
                timerTextView.text = timeLeft
            }

            override fun onFinish() {
                // 타이머 종료 시 원하는 동작 수행
                timerTextView.text = "00:00"
            }
        }

        // 타이머 시작
        countDownTimer.start()
    }

    private fun setEditTextFocusChangeListener() {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    binding.emailAuthenticationOkBtn.visibility = View.INVISIBLE
                    binding.emailAuthenticationOkCompleteBtn.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}