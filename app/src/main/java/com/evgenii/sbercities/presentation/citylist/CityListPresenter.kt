package com.evgenii.sbercities.presentation.citylist

import android.content.Context
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListPresenter(
    context: Context,
    private var citiesListView: CityListContract.View,
) : CityListContract.Presenter {

    private val repository = CityListRepositoryImpl(context)

    override fun onViewCreated() {
        val cityList = repository.loadCities()
        citiesListView.showCityList(cityList)
    }

    override fun onCitySelected(city: City) {
        citiesListView.showCityDetailInfo(city)
    }
}