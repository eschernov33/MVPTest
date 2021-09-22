package com.evgenii.sbercities.presentation.model

import androidx.annotation.DrawableRes

data class CityView(
    val cityId: Int,
    val cityName: String,
    val countryName: String,
    val population: String,
    val square: String,
    @DrawableRes
    val imgIconResId: Int,
    @DrawableRes
    val imgCityCardResId: Int,
    val description: String,
    val altitude: String,
    @DrawableRes
    var favoriteImg: Int,
)