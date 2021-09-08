package com.evgenii.sbercities.presentation

import android.app.Application
import com.evgenii.sbercities.models.CityModel

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        cityModel = CityModel()
    }

    companion object {
        lateinit var cityModel: CityModel
    }
}