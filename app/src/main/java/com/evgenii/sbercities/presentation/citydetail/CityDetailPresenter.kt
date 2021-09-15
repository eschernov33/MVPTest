package com.evgenii.sbercities.presentation.citydetail

import android.content.Context
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.CityListRepositoryImpl
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityDetailContract

class CityDetailPresenter(
    private val view: CityDetailContract.View,
    private val context: Context,
    private val repository: CityListRepositoryImpl,

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
            setPopulation(context.resources.getQuantityString(
                R.plurals.population_field,
                city.population,
                city.population
            ))
            setSquare(context.resources.getString(R.string.square_field, city.square))
            when (city.altitude) {
                City.NO_DATA -> setAltitude(context.getString(R.string.no_data))
                else -> {
                    setAltitude(context.resources.getString(R.string.altitude_field, city.altitude))
                }
            }
            setFavoriteButton(city.isFavorite)
        }
    }

    override fun onFavoriteClick(city: City) {
        city.isFavorite = !city.isFavorite
        repository.updateCity(city)
        view.setFavoriteButton(city.isFavorite)
    }
}