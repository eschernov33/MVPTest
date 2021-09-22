package com.evgenii.sbercities.presentation.contracts

import androidx.navigation.NavController

interface CityListContract {

    interface Presenter : CityListBaseContract.Presenter {
        fun onClickButtonToFavoritesScreen(navController: NavController)
    }
}