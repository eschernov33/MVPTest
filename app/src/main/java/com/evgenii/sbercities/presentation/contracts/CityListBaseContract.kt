package com.evgenii.sbercities.presentation.contracts

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.presentation.model.CityParam

interface CityListBaseContract {

    interface View {
        fun showCityList(cityList: List<CityParam>)
        fun updateCityList(cityList: List<CityParam>)
        fun navigateToCityDetail(cityId: Int, extras: FragmentNavigator.Extras)
    }

    interface Presenter {
        fun onCitySelected(cityId: Int, extras: FragmentNavigator.Extras)
        fun onFavoriteClick(cityId: Int)
        fun onFilterApply(query: String)
    }
}