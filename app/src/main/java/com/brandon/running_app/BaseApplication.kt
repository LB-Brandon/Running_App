package com.brandon.running_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp  // 의존성 주입을 원함을 선언 이후 manifest에 application:name 속성으로 BaseApplication을 설정
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}