package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa_android.databinding.ActivityAgreementBinding
import com.example.airconmoa_android.databinding.ActivityCreateUserBinding

class AgreementActivity : BaseActivityVB<ActivityAgreementBinding>(ActivityAgreementBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.agreementBackIv.setOnClickListener {
            finish()
        }

        binding.agreementFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.agreementAllAgreeBtn.setOnClickListener {
            setAllCheck()
        }

        binding.agreementAllAgreeSelectBtn.setOnClickListener {
            setAllUncheck()
        }

        binding.agreementAllAgreeIv.setOnClickListener {
            setAllCheck()
        }

        binding.agreementAllAgreeSelectIv.setOnClickListener {
            setAllUncheck()
        }

        // 1번 약관 토글
        binding.agreementUncheckIv1.setOnClickListener {
            binding.agreementCheckIv1.visibility = View.VISIBLE
            binding.agreementUncheckIv1.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv1.setOnClickListener {
            binding.agreementCheckIv1.visibility = View.INVISIBLE
            binding.agreementUncheckIv1.visibility = View.VISIBLE

            isComplete()
        }

        // 2번 약관 토글
        binding.agreementUncheckIv2.setOnClickListener {
            binding.agreementCheckIv2.visibility = View.VISIBLE
            binding.agreementUncheckIv2.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv2.setOnClickListener {
            binding.agreementCheckIv2.visibility = View.INVISIBLE
            binding.agreementUncheckIv2.visibility = View.VISIBLE

            isComplete()
        }

        // 3번 약관 토글
        binding.agreementUncheckIv3.setOnClickListener {
            binding.agreementCheckIv3.visibility = View.VISIBLE
            binding.agreementUncheckIv3.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv3.setOnClickListener {
            binding.agreementCheckIv3.visibility = View.INVISIBLE
            binding.agreementUncheckIv3.visibility = View.VISIBLE

            isComplete()
        }

        // 4번 약관 토글
        binding.agreementUncheckIv4.setOnClickListener {
            binding.agreementCheckIv4.visibility = View.VISIBLE
            binding.agreementUncheckIv4.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv4.setOnClickListener {
            binding.agreementCheckIv4.visibility = View.INVISIBLE
            binding.agreementUncheckIv4.visibility = View.VISIBLE

            isComplete()
        }

        // 5번 약관 토글
        binding.agreementUncheckIv5.setOnClickListener {
            binding.agreementCheckIv5.visibility = View.VISIBLE
            binding.agreementUncheckIv5.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv5.setOnClickListener {
            binding.agreementCheckIv5.visibility = View.INVISIBLE
            binding.agreementUncheckIv5.visibility = View.VISIBLE

            isComplete()
        }

        // 6번 약관 토글
        binding.agreementUncheckIv6.setOnClickListener {
            binding.agreementCheckIv6.visibility = View.VISIBLE
            binding.agreementUncheckIv6.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv6.setOnClickListener {
            binding.agreementCheckIv6.visibility = View.INVISIBLE
            binding.agreementUncheckIv6.visibility = View.VISIBLE

            isComplete()
        }

        // 7번 약관 토글
        binding.agreementUncheckIv7.setOnClickListener {
            binding.agreementCheckIv7.visibility = View.VISIBLE
            binding.agreementUncheckIv7.visibility = View.INVISIBLE

            isComplete()
        }

        binding.agreementCheckIv7.setOnClickListener {
            binding.agreementCheckIv7.visibility = View.INVISIBLE
            binding.agreementUncheckIv7.visibility = View.VISIBLE

            isComplete()
        }

        binding.agreementAgreeUncompleteBtn.setOnClickListener {
            Toast.makeText(this, "약관 동의를 먼저 진행해주세요.", Toast.LENGTH_SHORT).show()
        }

        binding.agreementAgreeCompleteBtn.setOnClickListener {
            val intent = Intent(this, EnterEmailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setAllCheck() {
        binding.agreementAllAgreeIv.visibility = View.INVISIBLE
        binding.agreementAllAgreeSelectIv.visibility = View.VISIBLE
        binding.agreementAllAgreeBtn.visibility = View.INVISIBLE
        binding.agreementAllAgreeSelectBtn.visibility = View.VISIBLE


        binding.agreementCheckIv1.visibility = View.VISIBLE
        binding.agreementUncheckIv1.visibility = View.INVISIBLE

        binding.agreementCheckIv2.visibility = View.VISIBLE
        binding.agreementUncheckIv2.visibility = View.INVISIBLE

        binding.agreementCheckIv3.visibility = View.VISIBLE
        binding.agreementUncheckIv3.visibility = View.INVISIBLE

        binding.agreementCheckIv4.visibility = View.VISIBLE
        binding.agreementUncheckIv4.visibility = View.INVISIBLE

        binding.agreementCheckIv5.visibility = View.VISIBLE
        binding.agreementUncheckIv5.visibility = View.INVISIBLE

        binding.agreementCheckIv6.visibility = View.VISIBLE
        binding.agreementUncheckIv6.visibility = View.INVISIBLE

        binding.agreementCheckIv7.visibility = View.VISIBLE
        binding.agreementUncheckIv7.visibility = View.INVISIBLE

        isComplete()
    }

    private fun setAllUncheck() {
        binding.agreementAllAgreeSelectIv.visibility = View.INVISIBLE
        binding.agreementAllAgreeIv.visibility = View.VISIBLE
        binding.agreementAllAgreeSelectBtn.visibility = View.INVISIBLE
        binding.agreementAllAgreeBtn.visibility = View.VISIBLE

        binding.agreementCheckIv1.visibility = View.INVISIBLE
        binding.agreementUncheckIv1.visibility = View.VISIBLE

        binding.agreementCheckIv2.visibility = View.INVISIBLE
        binding.agreementUncheckIv2.visibility = View.VISIBLE

        binding.agreementCheckIv3.visibility = View.INVISIBLE
        binding.agreementUncheckIv3.visibility = View.VISIBLE

        binding.agreementCheckIv4.visibility = View.INVISIBLE
        binding.agreementUncheckIv4.visibility = View.VISIBLE

        binding.agreementCheckIv5.visibility = View.INVISIBLE
        binding.agreementUncheckIv5.visibility = View.VISIBLE

        binding.agreementCheckIv6.visibility = View.INVISIBLE
        binding.agreementUncheckIv6.visibility = View.VISIBLE

        binding.agreementCheckIv7.visibility = View.INVISIBLE
        binding.agreementUncheckIv7.visibility = View.VISIBLE

        isComplete()
    }

    private fun isAllCheck() : Boolean {
        return (binding.agreementCheckIv1.visibility == View.VISIBLE
                && binding.agreementCheckIv2.visibility == View.VISIBLE
                && binding.agreementCheckIv3.visibility == View.VISIBLE
                && binding.agreementCheckIv4.visibility == View.VISIBLE
                && binding.agreementCheckIv5.visibility == View.VISIBLE
                && binding.agreementCheckIv6.visibility == View.VISIBLE
                && binding.agreementCheckIv7.visibility == View.VISIBLE)
    }

    private fun isPartialCheck() : Boolean {
        return (binding.agreementCheckIv1.visibility == View.VISIBLE
                && binding.agreementCheckIv2.visibility == View.VISIBLE
                && binding.agreementCheckIv3.visibility == View.VISIBLE
                && binding.agreementCheckIv4.visibility == View.VISIBLE
                && binding.agreementCheckIv7.visibility == View.VISIBLE)
    }

    private fun isComplete() {
        if(isAllCheck()) {
            binding.agreementAgreeUncompleteBtn.visibility = View.INVISIBLE
            binding.agreementAgreeCompleteBtn.visibility = View.VISIBLE
            binding.agreementAllAgreeSelectBtn.visibility = View.VISIBLE
            binding.agreementAllAgreeBtn.visibility = View.INVISIBLE
            binding.agreementAllAgreeIv.visibility = View.INVISIBLE
            binding.agreementAllAgreeSelectIv.visibility = View.VISIBLE
        }

        else if(isPartialCheck()) {
            binding.agreementAgreeUncompleteBtn.visibility = View.INVISIBLE
            binding.agreementAgreeCompleteBtn.visibility = View.VISIBLE
            binding.agreementAllAgreeIv.visibility = View.VISIBLE
            binding.agreementAllAgreeSelectIv.visibility = View.INVISIBLE
            binding.agreementAllAgreeBtn.visibility = View.VISIBLE
            binding.agreementAllAgreeSelectBtn.visibility = View.INVISIBLE
        }

        else {
            binding.agreementAgreeCompleteBtn.visibility = View.INVISIBLE
            binding.agreementAgreeUncompleteBtn.visibility = View.VISIBLE
            binding.agreementAllAgreeSelectBtn.visibility = View.INVISIBLE
            binding.agreementAllAgreeBtn.visibility = View.VISIBLE
            binding.agreementAllAgreeIv.visibility = View.VISIBLE
            binding.agreementAllAgreeSelectIv.visibility = View.INVISIBLE
        }
    }
}