package com.evgenii.sbercities.data

import com.evgenii.sbercities.models.City

interface CityListRepository {

    fun getCities(): List<City>

    fun updateCity(updatedCity: City)

}