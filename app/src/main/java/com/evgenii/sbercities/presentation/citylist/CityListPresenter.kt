package com.evgenii.sbercities.presentation.citylist

import android.content.Context
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListPresenter(
    private val citiesListView: CityListContract.View,
    private val model: CityListContract.Model,
) : CityListContract.Presenter {

    private lateinit var repository: CityListRepositoryImpl

    override fun onAttach(context: Context) {
        repository = CityListRepositoryImpl.getInstance(context)
        updateModel()
    }

    private fun updateModel() {
        if (!model.isLoadedData()) {
            model.updateCities(repository.loadCities())
        }
    }

    override fun onViewCreated() {
        citiesListView.showCityList(model.getCities())
    }

    override fun onCitySelected(city: City) {
        citiesListView.showCityDetailInfo(city)
    }

}