package com.evgenii.sbercities.presentation.contracts

interface CityListContract {

    interface View : CityListBaseContract.View {
        fun navigateToFavoritesScreen()
    }

    interface Presenter : CityListBaseContract.Presenter {
        fun onClickButtonToFavoritesScreen()
    }
}