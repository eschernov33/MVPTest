package com.evgenii.sbercities.mvp

import androidx.annotation.DrawableRes
import com.evgenii.sbercities.models.City

interface CityDetailContract {

    interface View {
        fun setHeaderImage(@DrawableRes resId: Int)
        fun setCityName(cityName: String)
        fun setCountryName(countryName: String)
        fun setPopulation(population: String)
        fun setSquare(square: String)
        fun setAltitude(altitude: String)
        fun setDescription(description: String)
        fun setFavoriteButton(isFavorite: Boolean)
    }

    interface Presenter {
        fun init(city: City)
        fun onFavoriteClick(city: City)
    }
}