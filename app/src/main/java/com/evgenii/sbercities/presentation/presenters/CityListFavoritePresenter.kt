package com.evgenii.sbercities.presentation.presenters

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.data.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract
import com.evgenii.sbercities.presentation.contracts.CityListFavoriteContract
import com.evgenii.sbercities.presentation.fragments.CityListFavoriteFragmentDirections
import com.evgenii.sbercities.presentation.mapper.CityMapper

class CityListFavoritePresenter(
    private val citiesListView: CityListBaseContract.View,
    repository: CityListRepository,
    context: Context?,
) : CityListBasePresenter(citiesListView, repository, context), CityListFavoriteContract.Presenter {

    private val cityUseCase = CityUseCase(repository)
    private val mapper = CityMapper(context)

    init {
        val cityList = cityUseCase.getCities(isFavorite = true)
        val cityListView = mapper.mapFromListEntity(cityList)
        citiesListView.showCityList(cityListView)
    }

    override fun updateCityList(query: String) {
        val updatedCityList = cityUseCase.getCities(query, isFavorite = true)
        val updatedCityListView = mapper.mapFromListEntity(updatedCityList)
        citiesListView.updateCityList(updatedCityListView)
    }

    override fun onActionBarBackButtonPressed(navController: NavController) {
        navController.popBackStack()
    }

    override fun onCitySelected(
        cityId: Int,
        extras: FragmentNavigator.Extras,
        navController: NavController,
    ) {
        navController.navigate(
            CityListFavoriteFragmentDirections.actionCityListFavoritesFragmentToCityDetailFragment(
                cityId),
            extras
        )
    }
}
