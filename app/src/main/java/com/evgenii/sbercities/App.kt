package com.evgenii.sbercities

import android.app.Application
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.presentation.citylist.CityModel

class App : Application() {

    val cityModel: CityModel by lazy {
        CityModel()
    }

    val repository: CityListRepositoryImpl by lazy {
        CityListRepositoryImpl(applicationContext)
    }

}