package com.brandon.running_app.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.brandon.running_app.db.RunningDatabase
import com.brandon.running_app.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.brandon.running_app.other.Constants.KEY_NAME
import com.brandon.running_app.other.Constants.KEY_WEIGHT
import com.brandon.running_app.other.Constants.RUNNING_DATABASE_NAME
import com.brandon.running_app.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)  // dependency 의 생성 시기와 삭제 시기 지정, Service, Activity, Fragment 등 다른 설정 존재
object AppModule {

    // InstallIn() 어노테이션을 통해 종속성의 생애주기를 설정하고
    // @Singleton은 그 생애주기 동안 하나의 객체만을 사용하겠 선언

    // 데이터베이스 생성 메뉴얼
    @Singleton // 이 함수의 결과로 생성한 종속성을 싱글톤으로 관리
    @Provides // 이 함수의 결과가 다른 종속성을 생성할 수 있음을 알림
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()


    // DAO 생성 메뉴얼, 이 메뉴얼에 전달되는 매개변수 db는 위 데이터 베이스 종속성 생성 메뉴얼에 따라 자동으로 생성되어 삽입
    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true)

}