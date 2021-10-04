package com.evgenii.sbercities.presentation.presenters

import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.mapper.CityMapper

class CityDetailPresenter(
    private val cityDetailView: CityDetailContract.View,
    private val cityUseCase: CityUseCase,
) : CityDetailContract.Presenter {

    private val mapper = CityMapper()
    private var cityId = CITY_ID_NOT_INIT

    override fun init(cityId: Int) {
        this.cityId = cityId
        val city = cityUseCase.getCityById(cityId)
        val cityView = mapper.mapFromEntity(city)
        cityDetailView.setCityValues(cityView)
        cityDetailView.setToolbar(cityView.cityName)
    }

    override fun onFavoriteClick() {
        val city = cityUseCase.getCityById(cityId)
        city.isFavorite = !city.isFavorite
        cityUseCase.updateCity(city)
        val updatedCity = cityUseCase.getCityById(city.cityId)
        val updatedCityView = mapper.mapFromEntity(updatedCity)
        cityDetailView.setFavoriteButton(updatedCityView.favoriteImg)
    }

    override fun onActionBarBackButtonPressed() {
        cityDetailView.navigateToBackScreen()
    }

    companion object {
        private const val CITY_ID_NOT_INIT = -1
    }
}