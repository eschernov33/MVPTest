package com.evgenii.sbercities.models

import android.os.Parcelable
import com.evgenii.sbercities.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val cityName: String,
    val countryName: String,
    val population: Int,
    val square: Int,
    val imgIcon: Int = R.drawable.city_moscow,
    val imgCityCard: Int = R.drawable.city_moscow,
    val description: String = NO_DESCRIPTION,
    val altitude: Int = NO_DATA,
) : Parcelable {

    companion object {
        const val NO_DATA = -1
        const val NO_DESCRIPTION = ""
    }
}