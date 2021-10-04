package com.evgenii.sbercities.presentation.presenters.models

import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.presentation.model.CityItem

class CityDetailFactory {

    val cityItemIsNotFavorite: CityItem =
        CityItem(
            CITY_ID_NOT_FAVORITE,
            CITY_NAME,
            CITY_COUNTRY_NAME,
            CITY_POPULATION,
            CITY_SQUARE,
            R.drawable.flag_msc,
            R.drawable.city_moscow,
            CITY_DESCRIPTION,
            CITY_ALTITUDE,
            R.drawable.ic_favorite_disable
        )

    val cityIsNotFavorite: City =
        City(
            CITY_ID_NOT_FAVORITE,
            CITY_NAME,
            CITY_COUNTRY_NAME,
            CITY_POPULATION,
            CITY_SQUARE,
            CITY_DESCRIPTION,
            CITY_ALTITUDE,
            CityType.MOSCOW,
            false
        )

    val cityIsFavorite: City =
        City(
            CITY_ID_FAVORITE,
            CITY_NAME,
            CITY_COUNTRY_NAME,
            CITY_POPULATION,
            CITY_SQUARE,
            CITY_DESCRIPTION,
            CITY_ALTITUDE,
            CityType.MOSCOW,
            true
        )

    companion object {
        const val CITY_ID_NOT_FAVORITE = 1
        const val CITY_ID_FAVORITE = 2
        private const val CITY_NAME = "Moscow"
        private const val CITY_COUNTRY_NAME = "Russia"
        private const val CITY_POPULATION = 800_000
        private const val CITY_SQUARE = 200
        private const val CITY_DESCRIPTION = "Some description"
        private const val CITY_ALTITUDE = 200
    }
}