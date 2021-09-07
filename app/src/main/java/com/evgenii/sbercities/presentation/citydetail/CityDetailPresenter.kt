package com.evgenii.sbercities.presentation.citydetail

import android.content.Context
import android.os.Bundle
import com.evgenii.sbercities.R
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityDetailContract

class CityDetailPresenter(
    private val view: CityDetailContract.View,
    private val context: Context,
) :
    CityDetailContract.Presenter {

    override fun onViewCreated(arguments: Bundle) {
        val city = arguments.getParcelable<City>(CityDetailFragment.EXTRA_KEY)
        city?.let {
            with(view) {
                setCityName(it.cityName)
                setCountryName(it.countryName)
                setDescription(it.description)
                setHeaderImage(it.imgCityCard)
                setPopulation(String.format(
                    context.getString(R.string.population_field),
                    it.population
                ))
                setSquare(String.format(
                    context.getString(R.string.square_field),
                    it.square
                ))
                if (it.altitude != City.NO_DATA)
                    setAltitude(String.format(
                        context.getString(R.string.altitude_field),
                        it.altitude
                    ))
                else
                    setAltitude(context.getString(R.string.no_data))
            }
        } ?: throw RuntimeException("City is not contains in arguments")
    }
}