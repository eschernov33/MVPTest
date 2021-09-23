package com.evgenii.sbercities.presentation.model

import androidx.annotation.DrawableRes

data class CityParam(
    val cityId: Int,
    val cityName: String,
    val countryName: String,
    val population: Int,
    val square: Int,
    @DrawableRes
    val imgIconResId: Int,
    @DrawableRes
    val imgCityCardResId: Int,
    val description: String,
    val altitude: Int,
    @DrawableRes
    var favoriteImg: Int,
)