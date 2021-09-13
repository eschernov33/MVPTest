package com.evgenii.sbercities.mvp

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.models.City

interface CityListFavoritesContract {

    interface View {
        fun showCityList(cityList: List<City>)
        fun showCityDetailInfo(city: City, extras: FragmentNavigator.Extras)
        fun updateCityList(cityList: List<City>)
    }

    interface Presenter {
        fun init()
        fun onCitySelected(city: City, view: android.view.View)
        fun onFavoriteClick(city: City)
        fun onFilterApply(query: String?)
    }
}