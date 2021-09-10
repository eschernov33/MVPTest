package com.evgenii.sbercities.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class City(
    val cityName: String,
    val countryName: String,
    val population: Int,
    val square: Int,
    val imgIcon: Int,
    val imgCityCard: Int,
    val description: String = NO_DESCRIPTION,
    val altitude: Int = NO_DATA,
) : Parcelable {

    companion object {
        const val NO_DATA = -1
        const val NO_DESCRIPTION = ""
    }
}