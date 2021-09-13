package com.evgenii.sbercities.presentation.citylistfavorites

import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListFavoritesContract

class CityListFavoritePresenter(
    private val citiesListView: CityListFavoritesContract.View,
    private val repository: CityListRepositoryImpl,
) : CityListFavoritesContract.Presenter {

    override fun init() {
        citiesListView.showCityList(repository.getCities(isFavorite = true))
    }

    override fun onCitySelected(city: City, view: View) {
        val extras = FragmentNavigatorExtras(view to city.getUniqueTransitionName())
        citiesListView.showCityDetailInfo(city, extras)
    }

    override fun onFavoriteClick(city: City) {
        val cityUpdate = city.copy(isFavorite = !city.isFavorite)
        repository.updateCity(cityUpdate)
        citiesListView.updateCityList(repository.getCities(isFavorite = true))
    }

    override fun onFilterApply(query: String?) {
        citiesListView.updateCityList(repository.getCities(query, isFavorite = true))
    }
}