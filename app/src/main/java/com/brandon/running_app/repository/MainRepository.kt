package com.brandon.running_app.repository

import com.brandon.running_app.db.Run
import com.brandon.running_app.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
) {
    // 데이터베이스의 기능 제공(DAO 객체 필요, 의존성), api 호출 기능 제공
    // repository의 일은 모든 데이터 소스에서 데이터를 수집하는 것
    // 현재는 room 데이터만 사용하지만 api 호출도 관리한다

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    // 아래가 suspend 함수가 아닌 이유는 getAllRunsSortedByDate()는 Livedata를 반환하고 이는 비동기식 이므로
    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

}