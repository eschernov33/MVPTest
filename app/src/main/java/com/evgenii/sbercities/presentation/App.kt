package com.evgenii.sbercities.presentation

import android.app.Application
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.mvp.CityListContract

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        cityListRepository = CityListRepositoryImpl(applicationContext)
    }

    companion object {
        lateinit var cityListRepository: CityListContract.Repository
    }
}