package com.evgenii.sbercities.data.repository

import com.evgenii.sbercities.domain.model.City

interface CityListRepository {

    fun getCityById(cityId: Int): City
    fun getCities(): List<City>
    fun updateCity(updatedCity: City)
}