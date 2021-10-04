package com.evgenii.sbercities.presentation.presenters

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListContract
import com.evgenii.sbercities.presentation.mapper.CityMapper

class CityListPresenter(
    private val citiesListView: CityListContract.View,
    private val cityUseCase: CityUseCase,
) : CityListBasePresenter(cityUseCase), CityListContract.Presenter {

    private val mapper = CityMapper()

    init {
        val cityList = cityUseCase.getCities()
        val cityListView = mapper.mapFromListEntity(cityList)
        citiesListView.showCityList(cityListView)
    }

    override fun onClickButtonToFavoritesScreen() {
        citiesListView.navigateToFavoritesScreen()
    }

    override fun onCitySelected(cityId: Int, extras: FragmentNavigator.Extras) =
        citiesListView.navigateToCityDetail(cityId, extras)

    override fun updateCityList() {
        val updatedCityList = cityUseCase.getCities(query)
        val updatedCityListView = mapper.mapFromListEntity(updatedCityList)
        citiesListView.updateCityList(updatedCityListView)
    }
}