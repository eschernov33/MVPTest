package com.evgenii.sbercities.presentation.citydetail

import android.content.Context
import android.os.Bundle
import com.evgenii.sbercities.R
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityDetailContract

class CityDetailPresenter(
    private val view: CityDetailContract.View,
    private val context: Context,
) : CityDetailContract.Presenter {

    override fun init(arguments: Bundle) {
        val city: City? = arguments.getParcelable(CityDetailFragment.EXTRA_KEY_CITY)
        city?.let {
            setViewsValue(it)
        } ?: throw RuntimeException("City is not contains in arguments")
    }

    private fun setViewsValue(city: City) {
        with(view) {
            setCityName(city.cityName)
            setCountryName(city.countryName)
            setDescription(city.description)
            setHeaderImage(city.imgCityCard)
            setPopulation(context.resources.getQuantityString(
                R.plurals.population_field,
                city.population,
                city.population
            ))
            setSquare(String.format(
                context.getString(R.string.square_field),
                city.square
            ))
            when (city.altitude) {
                City.NO_DATA -> setAltitude(context.getString(R.string.no_data))
                else -> {
                    setAltitude(String.format(
                        context.getString(R.string.altitude_field),
                        city.altitude
                    ))
                }
            }
        }
    }
}