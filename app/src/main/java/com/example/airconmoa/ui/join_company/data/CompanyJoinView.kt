package com.example.airconmoa.ui.join_company.data

interface CompanyJoinView {
    fun onLoginSuccess(code : Int, result : ResultCompanyJoin)
    fun onLoginFailure(message: String?)
}