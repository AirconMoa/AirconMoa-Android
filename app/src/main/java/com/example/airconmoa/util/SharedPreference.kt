package com.example.airconmoa.until

import com.example.airconmoa.config.App
import com.example.airconmoa.util.Constants

fun saveSi(location_si: String) {
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.putString(Constants.location_si, location_si)
    editor.apply()
}

fun getSi(): String? = App.sharedPreferences.getString(Constants.location_si, null)

fun removeSi() {
    val spf = App.sharedPreferences
    val editor = spf.edit()

    editor.remove(Constants.location_si)
    editor.apply()
}
