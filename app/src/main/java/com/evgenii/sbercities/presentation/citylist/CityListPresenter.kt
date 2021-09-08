package com.evgenii.sbercities.presentation.citylist

import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract
import com.evgenii.sbercities.presentation.App

class CityListPresenter(private var citiesListView: CityListContract.View) :
    CityListContract.Presenter {

    private val repository = App.cityListRepository

    override fun onViewCreated() {
        val cityList = repository.loadCities()
        citiesListView.showCityList(cityList)
    }

    override fun onCitySelected(city: City) {
        citiesListView.showCityDetailInfo(city)
    }
}