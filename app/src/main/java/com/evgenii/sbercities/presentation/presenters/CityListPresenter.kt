package com.evgenii.sbercities.presentation.presenters

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.data.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract
import com.evgenii.sbercities.presentation.contracts.CityListContract
import com.evgenii.sbercities.presentation.fragments.CityListFragmentDirections
import com.evgenii.sbercities.presentation.mapper.CityMapper

class CityListPresenter(
    private val citiesListView: CityListBaseContract.View,
    repository: CityListRepository,
    context: Context?,
) : CityListBasePresenter(citiesListView, repository, context), CityListContract.Presenter {

    private val cityUseCase = CityUseCase(repository)
    private val mapper = CityMapper(context)

    init {
        val cityList = cityUseCase.getCities()
        val cityListView = mapper.mapFromListEntity(cityList)
        citiesListView.showCityList(cityListView)
    }

    override fun onClickButtonToFavoritesScreen(navController: NavController) {
        navController.navigate(CityListFragmentDirections
            .actionCityListFragmentToCityListFavoritesFragment())
    }

    override fun onCitySelected(
        cityId: Int,
        extras: FragmentNavigator.Extras,
        navController: NavController,
    ) {
        navController.navigate(CityListFragmentDirections
            .actionCityListFragmentToCityDetailFragment(cityId), extras)
    }

    override fun updateCityList(query: String) {
        val updatedCityList = cityUseCase.getCities(query)
        val updatedCityListView = mapper.mapFromListEntity(updatedCityList)
        citiesListView.updateCityList(updatedCityListView)
    }
}