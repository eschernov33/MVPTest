package com.evgenii.sbercities

import android.app.Application
import com.evgenii.sbercities.data.repository.CityListRepositoryImpl

class App : Application() {

    val repository: CityListRepositoryImpl by lazy {
        CityListRepositoryImpl(applicationContext)
    }
}