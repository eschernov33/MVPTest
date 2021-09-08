package com.evgenii.sbercities.models

import com.evgenii.sbercities.mvp.CityListContract

class CityModel : CityListContract.Model {

    private var listCity = listOf<City>()

    override fun updateCities(cityList: List<City>) {
        listCity = cityList
    }

    override fun getCities() = listCity

    override fun isLoadedData() = listCity.isNotEmpty()

}