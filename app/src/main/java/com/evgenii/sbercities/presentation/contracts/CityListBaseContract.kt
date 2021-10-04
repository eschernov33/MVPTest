package com.evgenii.sbercities.presentation.contracts

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.presentation.model.CityItem

interface CityListBaseContract {

    interface View {
        fun showCityList(cityList: List<CityItem>)
        fun updateCityList(cityList: List<CityItem>)
        fun navigateToCityDetail(cityId: Int, extras: FragmentNavigator.Extras)
    }

    interface Presenter {
        fun onCitySelected(cityId: Int, extras: FragmentNavigator.Extras)
        fun onFavoriteClick(cityId: Int)
        fun onFilterApply(query: String)
    }
}