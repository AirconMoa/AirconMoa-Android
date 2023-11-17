package com.example.airconmoa.until

import com.example.airconmoa.config.App
import com.example.airconmoa.util.Constants

fun saveEmail(email: String){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.email,email)
    editor.apply()
}

fun getEmail():String? = App.sharedPreferences.getString(Constants.email,null)

fun removeEmail(){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.email)
    editor.apply()
}

fun savePassword(password: String){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.password,password)
    editor.apply()
}

fun getPassword():String? = App.sharedPreferences.getString(Constants.password,null)

fun removePassword(){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.password)
    editor.apply()
}

fun savePhonenum(phonenum: String){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.phonenum,phonenum)
    editor.apply()
}

fun getPhonenum():String? = App.sharedPreferences.getString(Constants.phonenum,null)

fun removePhonenum(){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.phonenum)
    editor.apply()
}

fun saveCompanyName(companyName: String){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.companyName,companyName)
    editor.apply()
}

fun getCompanyName():String? = App.sharedPreferences.getString(Constants.companyName,null)

fun removeCompanyName(){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.companyName)
    editor.apply()
}

fun saveAddress(address: String){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.address,address)
    editor.apply()
}

fun getAddress():String? = App.sharedPreferences.getString(Constants.address,null)

fun removeAddress(){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.address)
    editor.apply()
}

fun saveName(name: String){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.name,name)
    editor.apply()
}

fun getName():String? = App.sharedPreferences.getString(Constants.name,null)

fun removeName(){
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.name)
    editor.apply()
}