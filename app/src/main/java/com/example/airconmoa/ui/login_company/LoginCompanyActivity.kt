package com.example.airconmoa.ui.login_company

import android.content.Intent
import android.os.Bundle
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityLoginCompanyBinding
import com.example.airconmoa.ui.join_company.CompanyJoinFirstActivity
import com.example.airconmoa.ui.login_company.data.CompanyLoginDataSource
import com.example.airconmoa.ui.login_company.data.CompanyLoginView
import com.example.airconmoa.ui.login_company.data.Login
import com.example.airconmoa.ui.main_user.MainActivity

class LoginCompanyActivity : BaseActivityVB<ActivityLoginCompanyBinding>(ActivityLoginCompanyBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        with(binding){

            btnNext.setOnClickListener {
                login()
            }

            btnBack.setOnClickListener {
                val intent = Intent(this@LoginCompanyActivity,CompanyJoinFirstActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun login(){
        CompanyLoginDataSource().login(getuser(),object : CompanyLoginView{
            override fun onLoginSuccess(code: Int, result: String) {
                when(code){
                    1000->{
                        startMainActivity()
                    }
                }
            }

            override fun onLoginFailure(message: String?) {
                showCustomToast(message!!)
            }

        })
    }

    private fun getuser(): Login {
        return Login(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}