package com.example.airconmoa

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "64eccc279aa42b2d6d95605fcb8b3387")
    }
}