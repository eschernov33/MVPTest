package com.evgenii.sbercities.data

import com.evgenii.sbercities.models.City

interface CityListRepository {

    fun loadCities(): List<City>

}