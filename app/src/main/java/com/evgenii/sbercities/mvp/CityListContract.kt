package com.evgenii.sbercities.mvp

import android.content.Context
import com.evgenii.sbercities.models.City

interface CityListContract {

    interface View {
        fun showCityList(cityList: List<City>)
        fun showCityDetailInfo(city: City)
    }

    interface Presenter {
        fun onAttach(context: Context)
        fun onViewCreated()
        fun onCitySelected(city: City)
    }

    interface Model {
        fun updateCities(cityList: List<City>)
        fun getCities(): List<City>
        fun isLoadedData(): Boolean

    }

}