package com.evgenii.sbercities.presentation.contracts

interface CityListFavoriteContract {

    interface View : CityListBaseContract.View {
        fun navigateToBackScreen()
    }

    interface Presenter : CityListBaseContract.Presenter {
        fun onActionBarBackButtonPressed()
    }
}
