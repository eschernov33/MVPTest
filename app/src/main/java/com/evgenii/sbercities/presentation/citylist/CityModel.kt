package com.evgenii.sbercities.presentation.citylist

import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityModel : CityListContract.Model {

    private var listCity = listOf<City>()

    override fun updateCities(cityList: List<City>) {
        listCity = cityList
    }

    override fun getCities() =
        listCity

    override fun isDataLoaded() =
        listCity.isNotEmpty()
}