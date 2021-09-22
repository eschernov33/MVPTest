package com.evgenii.sbercities.data.repository

import android.content.Context
import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType

class CityListRepositoryImpl(private val context: Context) : CityListRepository {

    private var cityId = FIRST_CITY_ID

    private val listCity = mutableListOf<City>()

    init {
        createDefaultCitiesData()
    }

    override fun getCityById(cityId: Int): City =
        listCity.first { city -> city.cityId == cityId }

    override fun getCities() =
        listCity.toList()

    override fun updateCity(updatedCity: City) {
        listCity.find { city ->
            city.cityId == updatedCity.cityId
        }?.let { city ->
            city.isFavorite = updatedCity.isFavorite
        }
    }

    private fun createDefaultCitiesData() {
        with(listCity) {
            add(City(
                cityId++,
                context.getString(R.string.city_moscow),
                context.getString(R.string.country_russia),
                context.resources.getInteger(R.integer.population_msc_value),
                context.resources.getInteger(R.integer.square_msc_value),
                cityType = CityType.MOSCOW,
                description = context.getString(R.string.moscow_description),
                altitude = context.resources.getInteger(R.integer.altitude_msc_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_bangkok),
                context.getString(R.string.country_thailand),
                context.resources.getInteger(R.integer.population_bangkok_value),
                context.resources.getInteger(R.integer.square_bangkok_value),
                cityType = CityType.BANGKOK,
                description = context.getString(R.string.bangkok_description),
                altitude = context.resources.getInteger(R.integer.altitude_bangkok_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_madrid),
                context.getString(R.string.country_spain),
                context.resources.getInteger(R.integer.population_madrid_value),
                context.resources.getInteger(R.integer.square_madrid_value),
                cityType = CityType.MADRID,
                description = context.getString(R.string.madrid_description),
                altitude = context.resources.getInteger(R.integer.altitude_madrid_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_new_york),
                context.getString(R.string.country_usa),
                context.resources.getInteger(R.integer.population_new_york_value),
                context.resources.getInteger(R.integer.square_new_york_value),
                cityType = CityType.NEW_YORK,
                description = context.getString(R.string.new_york_description),
                altitude = context.resources.getInteger(R.integer.altitude_new_york_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_paris),
                context.getString(R.string.country_france),
                context.resources.getInteger(R.integer.population_paris_value),
                context.resources.getInteger(R.integer.square_paris_value),
                cityType = CityType.PARIS,
                description = context.getString(R.string.paris_description),
                altitude = context.resources.getInteger(R.integer.altitude_paris_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_amsterdam),
                context.getString(R.string.country_netherlands),
                context.resources.getInteger(R.integer.population_amsterdam_value),
                context.resources.getInteger(R.integer.square_amsterdam_value),
                cityType = CityType.AMSTERDAM,
                description = context.getString(R.string.amsterdam_description),
                altitude = context.resources.getInteger(R.integer.altitude_amsterdam_value)))
        }
    }

    companion object {
        private const val FIRST_CITY_ID = 1
    }
}