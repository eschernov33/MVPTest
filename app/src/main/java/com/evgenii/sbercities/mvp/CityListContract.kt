package com.evgenii.sbercities.mvp

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.models.City

interface CityListContract {

    interface View {
        fun showCityList(cityList: List<City>)
        fun showCityDetailInfo(city: City, extras: FragmentNavigator.Extras)
    }

    interface Presenter {
        fun init()
        fun onCitySelected(city: City, view: android.view.View)
    }

    interface Model {
        fun updateCities(cityList: List<City>)
        fun getCities(): List<City>
        fun isDataLoaded(): Boolean
    }
}