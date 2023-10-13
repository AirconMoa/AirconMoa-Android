package com.example.airconmoa.config

import com.google.gson.annotations.SerializedName

open class BaseResponse( // open class는 다른 클래스에서 상속할 수 있는 클래스이다.
    @SerializedName("code") val code : String = "",
    @SerializedName("msg") val msg : String = ""
)
