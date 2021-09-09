package com.evgenii.sbercities.presentation.citylist

import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListPresenter(
    private val citiesListView: CityListContract.View,
    private val cityModel: CityModel,
    private val repository: CityListRepositoryImpl,
) : CityListContract.Presenter {

    init {
        updateModel()
    }

    override fun onViewCreated() {
        citiesListView.showCityList(cityModel.getCities())
    }

    private fun updateModel() {
        if (!cityModel.isDataLoaded()) {
            cityModel.updateCities(repository.loadCities())
        }
    }

    override fun onCitySelected(city: City) {
        citiesListView.showCityDetailInfo(city)
    }

}