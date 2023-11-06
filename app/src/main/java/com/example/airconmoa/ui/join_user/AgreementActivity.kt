package com.example.airconmoa.ui.join_user

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityAgreementBinding

class AgreementActivity : BaseActivityVB<ActivityAgreementBinding>(ActivityAgreementBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.agreementBackIv.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

        binding.agreementFinishIv.setOnClickListener {
            val intent = Intent(this, NewMemberActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val toggleImageViews = listOf(
            binding.agreementUncheckIv1 to binding.agreementCheckIv1,
            binding.agreementUncheckIv2 to binding.agreementCheckIv2,
            binding.agreementUncheckIv3 to binding.agreementCheckIv3,
            binding.agreementUncheckIv4 to binding.agreementCheckIv4,
            binding.agreementUncheckIv5 to binding.agreementCheckIv5,
            binding.agreementUncheckIv6 to binding.agreementCheckIv6,
            binding.agreementUncheckIv7 to binding.agreementCheckIv7
        )

        toggleImageViews.forEach { (uncheckIv, checkIv) ->
            uncheckIv.setOnClickListener {
                checkIv.visibility = View.VISIBLE
                uncheckIv.visibility = View.INVISIBLE
                isComplete()
            }

            checkIv.setOnClickListener {
                checkIv.visibility = View.INVISIBLE
                uncheckIv.visibility = View.VISIBLE
                isComplete()
            }
        }

        val toggleAllButtons = listOf(
            binding.agreementAllAgreeBtn to { setAllCheck() },
            binding.agreementAllAgreeSelectBtn to { setAllUncheck() },
            binding.agreementAllAgreeIv to { setAllCheck() },
            binding.agreementAllAgreeSelectIv to { setAllUncheck() }
        )

        toggleAllButtons.forEach { (button, action) ->
            button.setOnClickListener { action() }
        }

        binding.agreementAgreeUncompleteBtn.setOnClickListener {
            showCustomToast("약관 동의를 먼저 진행해주세요.")
        }

        binding.agreementAgreeCompleteBtn.setOnClickListener {
            val intent = Intent(this, EnterEmailActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        setFullScreen()
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