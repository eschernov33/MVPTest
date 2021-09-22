package com.evgenii.sbercities.presentation.presenters

import android.content.Context
import androidx.navigation.NavController
import com.evgenii.sbercities.data.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.mapper.CityMapper

class CityDetailPresenter(
    private val view: CityDetailContract.View,
    repository: CityListRepository,
    context: Context?,
) : CityDetailContract.Presenter {

    private val cityUseCase = CityUseCase(repository)
    private val mapper = CityMapper(context)

    override fun init(cityId: Int) {
        val city = cityUseCase.getCityById(cityId)
        val cityView = mapper.mapFromEntity(city)
        view.setCityValues(cityView)
        view.setActionBar(cityView.cityName)
    }

    override fun onFavoriteClick(cityId: Int) {
        val city = cityUseCase.getCityById(cityId)
        city.isFavorite = !city.isFavorite
        cityUseCase.updateCity(city)
        val updatedCity = cityUseCase.getCityById(city.cityId)
        val updatedCityView = mapper.mapFromEntity(updatedCity)
        view.setFavoriteButton(updatedCityView.favoriteImg)
    }

    override fun onActionBarBackButtonPressed(navController: NavController) {
        navController.popBackStack()
    }
}