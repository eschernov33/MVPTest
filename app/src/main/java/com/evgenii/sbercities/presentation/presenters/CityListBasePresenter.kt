package com.evgenii.sbercities.presentation.presenters

import android.content.Context
import com.evgenii.sbercities.data.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract
import com.evgenii.sbercities.presentation.mapper.CityMapper

abstract class CityListBasePresenter(
    citiesListView: CityListBaseContract.View,
    repository: CityListRepository,
    context: Context?,
) : CityListBaseContract.Presenter {

    private val cityUseCase = CityUseCase(repository)
    private val mapper = CityMapper(context)

    init {
        val cityList = cityUseCase.getCities()
        val cityListView = mapper.mapFromListEntity(cityList)
        citiesListView.showCityList(cityListView)
    }

    override fun onFavoriteClick(cityId: Int, query: String) {
        val city = cityUseCase.getCityById(cityId)
        city.isFavorite = !city.isFavorite
        cityUseCase.updateCity(city)
        updateCityList(query)
    }

    override fun onFilterApply(query: String) {
        updateCityList(query)
    }

    abstract fun updateCityList(query: String)
}