package com.evgenii.sbercities.data

import com.evgenii.sbercities.models.City

interface CityListRepository {

    fun getCities(filterStroke: String? = null, isFavorite: Boolean = false): List<City>

    fun updateCity(updatedCity: City)
}