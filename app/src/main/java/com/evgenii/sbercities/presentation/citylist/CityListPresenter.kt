package com.evgenii.sbercities.presentation.citylist

import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListPresenter(
    private val citiesListView: CityListContract.View,
    private val cityModel: CityModel,
    private val repository: CityListRepositoryImpl,
) : CityListContract.Presenter {

    override fun init() {
        updateModel()
        citiesListView.showCityList(cityModel.getCities())
    }

    private fun updateModel() {
        if (!cityModel.isDataLoaded()) {
            cityModel.updateCities(repository.getCities())
        }
    }

    override fun onCitySelected(city: City, view: View) {
        val extras = FragmentNavigatorExtras(view to city.getUniqueTransitionName())
        citiesListView.showCityDetailInfo(city, extras)
    }
}