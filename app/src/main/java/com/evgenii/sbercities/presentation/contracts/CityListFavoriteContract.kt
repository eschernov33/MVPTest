package com.evgenii.sbercities.presentation.contracts

import androidx.navigation.NavController

interface CityListFavoriteContract {

    interface Presenter : CityListBaseContract.Presenter {
        fun onActionBarBackButtonPressed(navController: NavController)
    }
}
