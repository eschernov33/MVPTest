package com.evgenii.sbercities.presentation.presenters

import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract

abstract class CityListBasePresenter(
    private val cityUseCase: CityUseCase,
) : CityListBaseContract.Presenter {

    protected var query: String = EMPTY_QUERY

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
        private const val EMPTY_QUERY = ""
    }
}