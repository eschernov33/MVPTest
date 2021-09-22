package com.evgenii.sbercities.presentation.contracts

import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import com.evgenii.sbercities.presentation.model.CityView

interface CityDetailContract {

    interface View {
        fun setCityValues(city: CityView)
        fun setFavoriteButton(@DrawableRes imgRes: Int)
        fun setActionBar(title: String)
    }

    interface Presenter {
        fun init(cityId: Int)
        fun onFavoriteClick(cityId: Int)
        fun onActionBarBackButtonPressed(navController: NavController)
    }
}