package com.brandon.running_app.di

import android.content.Context
import androidx.room.Room
import com.brandon.running_app.db.RunningDatabase
import com.brandon.running_app.other.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)  // dependency 의 유지 기간 설정, Service, Activity, Fragment 등 다른 설정 존재
object AppModule {

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




}