package com.example.airconmoa.ui.login_company.data


interface CompanyLoginView {
    fun onLoginSuccess(code : Int, result : String)
    fun onLoginFailure(message: String?)

}