package com.evgenii.sbercities.presentation.presenters

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListFavoriteContract
import com.evgenii.sbercities.presentation.mapper.CityMapper

class CityListFavoritePresenter(
    private val citiesListView: CityListFavoriteContract.View,
    private val mapper: CityMapper,
    private val cityUseCase: CityUseCase,
) : CityListBasePresenter(citiesListView, mapper, cityUseCase), CityListFavoriteContract.Presenter {

    init {
        val cityList = cityUseCase.getCities(isFavorite = true)
        val cityListView = mapper.mapFromListEntity(cityList)
        citiesListView.showCityList(cityListView)
    }

    override fun updateCityList() {
        val updatedCityList = cityUseCase.getCities(query, isFavorite = true)
        val updatedCityListView = mapper.mapFromListEntity(updatedCityList)
        citiesListView.updateCityList(updatedCityListView)
    }

    override fun onActionBarBackButtonPressed() {
        citiesListView.navigateToBackScreen()
    }

    override fun onCitySelected(cityId: Int, extras: FragmentNavigator.Extras) =
        citiesListView.navigateToCityDetail(cityId, extras)
}
