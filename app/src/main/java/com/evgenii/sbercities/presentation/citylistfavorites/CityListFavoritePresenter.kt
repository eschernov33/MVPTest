package com.evgenii.sbercities.presentation.citylistfavorites

import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract
import com.evgenii.sbercities.presentation.citylist.CityListPresenter

class CityListFavoritePresenter(
    private val citiesListView: CityListContract.View,
    private val repository: CityListRepositoryImpl,
) : CityListPresenter(citiesListView, repository) {

    override fun init() {
        citiesListView.showCityList(repository.getCities(isFavorite = true))
    }

    override fun onFavoriteClick(city: City, query: String?) {
        val cityUpdate = city.copy(isFavorite = !city.isFavorite)
        repository.updateCity(cityUpdate)
        citiesListView.updateCityList(repository.getCities(isFavorite = true))
    }

    override fun onFilterApply(query: String?) {
        citiesListView.updateCityList(repository.getCities(query, isFavorite = true))
    }
}