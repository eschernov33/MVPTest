package com.evgenii.sbercities.presentation.contracts

import androidx.annotation.DrawableRes
import com.evgenii.sbercities.presentation.model.CityItem

interface CityDetailContract {

    interface View {
        fun setCityValues(city: CityItem)
        fun setFavoriteButton(@DrawableRes imgRes: Int)
        fun setToolbar(title: String)
        fun navigateToBackScreen()
    }

    interface Presenter {
        fun init(cityId: Int)
        fun onFavoriteClick()
        fun onActionBarBackButtonPressed()
    }
}