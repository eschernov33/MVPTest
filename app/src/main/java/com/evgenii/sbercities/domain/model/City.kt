package com.evgenii.sbercities.domain.model

class City(
    val cityId: Int,
    val cityName: String,
    val countryName: String,
    val population: Int,
    val square: Int,
    val description: String = NO_DESCRIPTION,
    val altitude: Int = NO_DATA,
    val cityType: CityType,
    var isFavorite: Boolean = false,
) {

    companion object {
        const val NO_DATA = -1
        const val NO_DESCRIPTION = ""
    }
}