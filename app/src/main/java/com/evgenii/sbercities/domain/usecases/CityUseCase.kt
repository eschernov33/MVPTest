package com.evgenii.sbercities.domain.usecases

import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.repository.CityListRepository

class CityUseCase(private val repository: CityListRepository) {

    fun getCityById(cityId: Int): City =
        repository.getCityById(cityId)

    fun getCities(filterStroke: String = "", isFavorite: Boolean = false): List<City> {
        var cities = repository.getCities()
        if (isFavorite) {
            cities = cities.filter(City::isFavorite)
        }
        if (filterStroke.isNotEmpty()) {
            cities = cities.filter { city ->
                city.cityName.contains(filterStroke, true) ||
                        city.countryName.contains(filterStroke, true)
            }
        }
        return cities
    }

    fun updateCity(updatedCity: City) =
        repository.updateCity(updatedCity)
}