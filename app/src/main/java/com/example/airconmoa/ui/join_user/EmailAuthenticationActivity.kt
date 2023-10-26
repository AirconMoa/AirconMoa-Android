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
import android.widget.Toast
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
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
                val textView = binding.emailAuthenticationAuthUncompleteTv
                textView.text = "뒤로 가기 버튼을 눌러 재인증을 시도하세요."
                textView.visibility = View.VISIBLE
            }
        }

        // 타이머 시작
        countDownTimer.start()

        binding.emailAuthenticationOkBtn.setOnClickListener {
            Toast.makeText(this, "인증번호를 올바로 입력해주세요.", Toast.LENGTH_SHORT).show()
        }

        binding.emailAuthenticationNextBtn.setOnClickListener {
            val intent = Intent(this, EnterPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setEditTextFocusChangeListener() {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText1.setBackgroundResource(R.drawable.edit_text_custom_selected)
                    editText2.requestFocus()
                }
                else {
                    editText1.setBackgroundResource(R.drawable.edit_text_custom_gray)
                }
                checkComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText2.setBackgroundResource(R.drawable.edit_text_custom_selected)
                    editText3.requestFocus()
                }
                else {
                    editText2.setBackgroundResource(R.drawable.edit_text_custom_gray)
                }
                checkComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText3.setBackgroundResource(R.drawable.edit_text_custom_selected)
                    editText4.requestFocus()
                }
                else {
                    editText3.setBackgroundResource(R.drawable.edit_text_custom_gray)
                }
                checkComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText4.setBackgroundResource(R.drawable.edit_text_custom_selected)
                    editText5.requestFocus()
                }
                else {
                    editText4.setBackgroundResource(R.drawable.edit_text_custom_gray)
                }
                checkComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText5.setBackgroundResource(R.drawable.edit_text_custom_selected)
                    editText6.requestFocus()
                }
                else {
                    editText5.setBackgroundResource(R.drawable.edit_text_custom_gray)
                }
                checkComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        editText6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    editText6.setBackgroundResource(R.drawable.edit_text_custom_selected)
                }
                else {
                    editText6.setBackgroundResource(R.drawable.edit_text_custom_gray)
                }
                checkComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun checkComplete() {
        if(editText1.text!!.length == 1
            && editText2.text!!.length == 1
            && editText3.text!!.length == 1
            && editText4.text!!.length == 1
            && editText5.text!!.length == 1
            && editText6.text!!.length == 1) {
            binding.emailAuthenticationOkBtn.visibility = View.INVISIBLE
            binding.emailAuthenticationOkCompleteBtn.visibility = View.VISIBLE
        } else {
            binding.emailAuthenticationOkBtn.visibility = View.VISIBLE
            binding.emailAuthenticationOkCompleteBtn.visibility = View.INVISIBLE
        }
    }
}