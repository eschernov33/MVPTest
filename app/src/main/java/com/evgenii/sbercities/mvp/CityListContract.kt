package com.evgenii.sbercities.mvp

import com.evgenii.sbercities.models.City

interface CityListContract {

    interface View {
        fun showCityList(cityList: List<City>)
        fun showCityDetailInfo(city: City)
    }

    interface Presenter {
        fun onViewCreated()
        fun onCitySelected(city: City)
    }

    interface Repository {
        fun loadCities(): List<City>
    }
}