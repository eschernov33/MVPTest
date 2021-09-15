package com.evgenii.sbercities.presentation.citylist

import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

open class CityListPresenter(
    private val citiesListView: CityListContract.View,
    private val repository: CityListRepositoryImpl,
) : CityListContract.Presenter {

    override fun init() {
        citiesListView.showCityList(repository.getCities())
    }

    override fun onCitySelected(city: City, view: View) {
        val extras = FragmentNavigatorExtras(view to city.getUniqueTransitionName())
        citiesListView.showCityDetailInfo(city, extras)
    }

    override fun onFavoriteClick(city: City, query: String?) {
        city.isFavorite = !city.isFavorite
        repository.updateCity(city)
        citiesListView.updateCityList(repository.getCities(query))
    }

    override fun onFilterApply(query: String?) {
        citiesListView.updateCityList(repository.getCities(query))
    }
}