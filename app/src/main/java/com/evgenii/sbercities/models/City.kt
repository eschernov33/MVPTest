package com.evgenii.sbercities.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val cityId: Int,
    val cityName: String,
    val countryName: String,
    val population: Int,
    val square: Int,
    @DrawableRes
    val imgIconResId: Int,
    @DrawableRes
    val imgCityCardResId: Int,
    val description: String = NO_DESCRIPTION,
    val altitude: Int = NO_DATA,
    var isFavorite: Boolean = false,
) : Parcelable {

    fun getUniqueTransitionName() =
        "$TRANSITION_NAME_PREFIX$cityId"

    companion object {
        const val NO_DATA = -1
        const val NO_DESCRIPTION = ""
        const val TRANSITION_NAME_PREFIX = "imageHeader_"
    }
}