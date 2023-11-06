package com.example.airconmoa.ui.mypage_user

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseFragmentVB
import com.example.airconmoa.databinding.FragmentUserEditMypageBinding


class UserMypageEditFragment : BaseFragmentVB<FragmentUserEditMypageBinding>(FragmentUserEditMypageBinding::bind, R.layout.fragment_user_edit_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.unsubscribeBtn.text=spannableText()



        with(binding) {
            nameeditTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (nameeditTv.length() > 0) {
                        editCloseBtn1.visibility = View.VISIBLE
                    } else {
                        editCloseBtn1.visibility = View.GONE
                    }

                }
            })

            phoneeditTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (phoneeditTv.length() > 0) {
                        editCloseBtn2.visibility = View.VISIBLE
                    } else {
                        editCloseBtn2.visibility = View.GONE
                    }

                }
            })
            nicknameeditTv.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (nicknameeditTv.length() > 0) {
                        editCloseBtn3.visibility = View.VISIBLE
                    } else {
                        editCloseBtn3.visibility = View.GONE
                    }

                }
            })
           editCloseBtn1.setOnClickListener { nameeditTv.text=null }
           editCloseBtn2.setOnClickListener {  phoneeditTv.text=null }
            editCloseBtn3.setOnClickListener {  nicknameeditTv.text=null }
        }

    }
    fun spannableText(): SpannableStringBuilder {
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.airconmoa_main))

        val textData:String="모아를 탈퇴하려면 여기를 눌러주세요"
        val spannable= SpannableStringBuilder(textData)
        spannable.setSpan(colorSpan,10,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannable
    }
}