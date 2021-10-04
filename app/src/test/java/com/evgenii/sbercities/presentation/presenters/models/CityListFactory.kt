package com.evgenii.sbercities.presentation.presenters.models

import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.presentation.model.CityItem

class CityListFactory {

    val cityMoscow: City = City(
        CITY_MOSCOW_ID,
        CITY_MOSCOW_NAME,
        CITY_MOSCOW_COUNTRY_NAME,
        CITY_MOSCOW_POPULATION,
        CITY_MOSCOW_SQUARE,
        CITY_MOSCOW_DESCRIPTION,
        CITY_MOSCOW_ALTITUDE,
        CityType.MOSCOW,
        CITY_MOSCOW_IS_FAVORITE
    )

    private val cityMadrid = City(
        CITY_MADRID_ID,
        CITY_MADRID_NAME,
        CITY_MADRID_COUNTRY_NAME,
        CITY_MADRID_POPULATION,
        CITY_MADRID_SQUARE,
        CITY_MADRID_DESCRIPTION,
        CITY_MADRID_ALTITUDE,
        CityType.MADRID,
        CITY_MADRID_IS_FAVORITE
    )

    val cityList: List<City> = listOf(
        cityMoscow, cityMadrid
    )

    private val cityItemMoscow = CityItem(
        CITY_MOSCOW_ID,
        CITY_MOSCOW_NAME,
        CITY_MOSCOW_COUNTRY_NAME,
        CITY_MOSCOW_POPULATION,
        CITY_MOSCOW_SQUARE,
        R.drawable.flag_msc,
        R.drawable.city_moscow,
        CITY_MOSCOW_DESCRIPTION,
        CITY_MOSCOW_ALTITUDE,
        R.drawable.ic_favorite_enable
    )

    private val cityItemMadrid = CityItem(
        CITY_MADRID_ID,
        CITY_MADRID_NAME,
        CITY_MADRID_COUNTRY_NAME,
        CITY_MADRID_POPULATION,
        CITY_MADRID_SQUARE,
        R.drawable.flag_madrid,
        R.drawable.city_madrid,
        CITY_MADRID_DESCRIPTION,
        CITY_MADRID_ALTITUDE,
        R.drawable.ic_favorite_enable
    )

    val cityItemList: List<CityItem> = listOf(
        cityItemMoscow, cityItemMadrid
    )

    val cityListItemQueryMA: List<CityItem> = listOf(
        cityItemMadrid
    )

    companion object {
        const val CITY_MOSCOW_ID = 1
        private const val CITY_MOSCOW_NAME = "Moscow"
        private const val CITY_MOSCOW_COUNTRY_NAME = "Russia"
        private const val CITY_MOSCOW_POPULATION = 800_000
        private const val CITY_MOSCOW_SQUARE = 200
        private const val CITY_MOSCOW_DESCRIPTION = "Some description"
        private const val CITY_MOSCOW_ALTITUDE = 200
        private const val CITY_MOSCOW_IS_FAVORITE = true

        const val CITY_MADRID_ID = 2
        private const val CITY_MADRID_NAME = "Madrid"
        private const val CITY_MADRID_COUNTRY_NAME = "Spain"
        private const val CITY_MADRID_POPULATION = 100_000
        private const val CITY_MADRID_SQUARE = 300
        private const val CITY_MADRID_DESCRIPTION = "Some description"
        private const val CITY_MADRID_ALTITUDE = 300
        private const val CITY_MADRID_IS_FAVORITE = true

        const val FILTER_QUERY_MADRID = "ma"
        const val FILTER_QUERY_EMPTY = ""
        const val FILTER_QUERY_NOT_CONTAINS = "Lc"
    }
}