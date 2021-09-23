package com.evgenii.sbercities.presentation.presenters

import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract
import com.evgenii.sbercities.presentation.mapper.CityMapper

abstract class CityListBasePresenter(
    citiesListView: CityListBaseContract.View,
    mapper: CityMapper,
    private val cityUseCase: CityUseCase,
) : CityListBaseContract.Presenter {

    protected var query: String = EMPTY_QUERY

    init {
        val cityList = cityUseCase.getCities()
        val cityListView = mapper.mapFromListEntity(cityList)
        citiesListView.showCityList(cityListView)
    }

    override fun onFavoriteClick(cityId: Int) {
        val city = cityUseCase.getCityById(cityId)
        city.isFavorite = !city.isFavorite
        cityUseCase.updateCity(city)
        updateCityList()
    }

    override fun onFilterApply(query: String) {
        this.query = query
        updateCityList()
    }

    abstract fun updateCityList()

    companion object {
        const val EMPTY_QUERY = ""
    }
}