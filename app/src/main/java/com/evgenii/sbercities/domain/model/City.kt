package com.evgenii.sbercities.domain.model

class City(
    val cityId: Int,
    val cityName: String,
    val countryName: String,
    val population: Int,
    val square: Int,
    val description: String,
    val altitude: Int,
    val cityType: CityType,
    var isFavorite: Boolean = false,
)