package com.evgenii.sbercities.presentation.contracts

import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.presentation.model.CityView

interface CityListBaseContract {

    interface View {
        fun showCityList(cityList: List<CityView>)
        fun updateCityList(cityList: List<CityView>)
    }

    interface Presenter {
        fun onCitySelected(
            cityId: Int,
            extras: FragmentNavigator.Extras,
            navController: NavController,
        )

        fun onFavoriteClick(cityId: Int, query: String = "")
        fun onFilterApply(query: String)
    }
}