package com.evgenii.sbercities.data

import android.content.Context
import com.evgenii.sbercities.R
import com.evgenii.sbercities.models.City

class CityListRepositoryImpl(private val context: Context) : CityListRepository {

    private var cityId = FIRST_CITY_ID

    private val listCity = mutableListOf<City>()

    init {
        createDefaultCitiesData()
    }

    override fun getCities(filterStroke: String?, isFavorite: Boolean): List<City> {
        var filteredListCity = listCity.toList()
        if (isFavorite) {
            filteredListCity = filteredListCity.filter(City::isFavorite)
        }
        if (filterStroke != null && filterStroke.isNotEmpty()) {
            filteredListCity = filteredListCity.filter {
                it.cityName.contains(filterStroke, true) ||
                        it.countryName.contains(filterStroke, true)
            }
        }
        return filteredListCity
    }

    override fun updateCity(updatedCity: City) {
        listCity.find { it.cityId == updatedCity.cityId }?.let {
            it.isFavorite = updatedCity.isFavorite
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
                description = context.getString(R.string.moscow_description),
                imgCityCardResId = R.drawable.city_moscow,
                imgIconResId = R.drawable.flag_msc,
                altitude = context.resources.getInteger(R.integer.altitude_msc_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_bangkok),
                context.getString(R.string.country_thailand),
                context.resources.getInteger(R.integer.population_bangkok_value),
                context.resources.getInteger(R.integer.square_bangkok_value),
                description = context.getString(R.string.bangkok_description),
                imgCityCardResId = R.drawable.city_bangkok,
                imgIconResId = R.drawable.flag_bangkok,
                altitude = context.resources.getInteger(R.integer.altitude_bangkok_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_madrid),
                context.getString(R.string.country_spain),
                context.resources.getInteger(R.integer.population_madrid_value),
                context.resources.getInteger(R.integer.square_madrid_value),
                description = context.getString(R.string.madrid_description),
                imgCityCardResId = R.drawable.city_madrid,
                imgIconResId = R.drawable.flag_madrid,
                altitude = context.resources.getInteger(R.integer.altitude_madrid_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_new_york),
                context.getString(R.string.country_usa),
                context.resources.getInteger(R.integer.population_new_york_value),
                context.resources.getInteger(R.integer.square_new_york_value),
                description = context.getString(R.string.new_york_description),
                imgCityCardResId = R.drawable.city_new_york,
                imgIconResId = R.drawable.flag_new_york,
                altitude = context.resources.getInteger(R.integer.altitude_new_york_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_paris),
                context.getString(R.string.country_france),
                context.resources.getInteger(R.integer.population_paris_value),
                context.resources.getInteger(R.integer.square_paris_value),
                description = context.getString(R.string.paris_description),
                imgCityCardResId = R.drawable.city_paris,
                imgIconResId = R.drawable.flag_paris,
                altitude = context.resources.getInteger(R.integer.altitude_paris_value)))

            add(City(
                cityId++,
                context.getString(R.string.city_amsterdam),
                context.getString(R.string.country_netherlands),
                context.resources.getInteger(R.integer.population_amsterdam_value),
                context.resources.getInteger(R.integer.square_amsterdam_value),
                description = context.getString(R.string.amsterdam_description),
                imgCityCardResId = R.drawable.city_amsterdam,
                imgIconResId = R.drawable.flag_amsterdam,
                altitude = context.resources.getInteger(R.integer.altitude_amsterdam_value)))
        }
    }

    companion object {
        private const val FIRST_CITY_ID = 1
    }
}