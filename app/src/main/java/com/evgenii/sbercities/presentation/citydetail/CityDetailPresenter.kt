package com.evgenii.sbercities.presentation.citydetail

import com.evgenii.sbercities.data.CityListRepository
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityDetailContract

class CityDetailPresenter(
    private val view: CityDetailContract.View,
    private val repository: CityListRepository
) : CityDetailContract.Presenter {

    override fun init(city: City) {
        setViewsValue(city)
    }

    private fun setViewsValue(city: City) {
        with(view) {
            setCityName(city.cityName)
            setCountryName(city.countryName)
            setDescription(city.description)
            setHeaderImage(city.imgCityCardResId)
            setPopulation(city.population)
            setSquare(city.square)
            setAltitude(city.altitude)
            setFavoriteButton(city.isFavorite)
        }
    }

    override fun onFavoriteClick(city: City) {
        city.isFavorite = !city.isFavorite
        repository.updateCity(city)
        view.setFavoriteButton(city.isFavorite)
    }
}