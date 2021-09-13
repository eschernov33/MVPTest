package com.evgenii.sbercities.mvp

import com.evgenii.sbercities.models.City

interface CityDetailContract {

    interface View {
        fun setHeaderImage(resId: Int)
        fun setCityName(cityName: String)
        fun setCountryName(countryName: String)
        fun setPopulation(population: String)
        fun setSquare(square: String)
        fun setAltitude(altitude: String)
        fun setDescription(description: String)
    }

    interface Presenter {
        fun init(city: City)
    }
}