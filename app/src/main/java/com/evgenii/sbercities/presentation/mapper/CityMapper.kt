package com.evgenii.sbercities.presentation.mapper

import android.content.Context
import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.presentation.model.CityView

class CityMapper(private val context: Context?) {

    fun mapFromEntity(item: City) =
        CityView(
            item.cityId,
            item.cityName,
            item.countryName,
            getPopulation(item.population),
            getSquare(item.square),
            getIcon(item.cityType),
            getImage(item.cityType),
            item.description,
            getAltitude(item.altitude),
            getFavoriteImg(item.isFavorite)
        )

    private fun getAltitude(altitude: Int): String {
        return context?.let {
            when (altitude) {
                City.NO_DATA -> it.resources.getString(R.string.no_data)
                else -> {
                    it.resources.getString(R.string.altitude_field, altitude)
                }
            }
        } ?: ""
    }

    private fun getSquare(square: Int): String {
        return context?.resources?.getString(
            R.string.square_field, square) ?: ""
    }

    private fun getPopulation(population: Int): String {
        return context?.resources?.getQuantityString(
            R.plurals.population_field,
            population,
            population) ?: ""
    }

    fun mapFromListEntity(list: List<City>) =
        list.map(this::mapFromEntity)

    private fun getFavoriteImg(isFavorite: Boolean) =
        if (isFavorite) R.drawable.ic_favorite_enable
        else R.drawable.ic_favorite_disable

    private fun getImage(cityType: CityType) =
        when (cityType) {
            CityType.PARIS -> R.drawable.city_paris
            CityType.AMSTERDAM -> R.drawable.city_amsterdam
            CityType.NEW_YORK -> R.drawable.city_new_york
            CityType.MADRID -> R.drawable.city_madrid
            CityType.BANGKOK -> R.drawable.city_bangkok
            else -> R.drawable.city_moscow
        }

    private fun getIcon(cityType: CityType) =
        when (cityType) {
            CityType.PARIS -> R.drawable.flag_paris
            CityType.AMSTERDAM -> R.drawable.flag_amsterdam
            CityType.NEW_YORK -> R.drawable.flag_new_york
            CityType.MADRID -> R.drawable.flag_madrid
            CityType.BANGKOK -> R.drawable.flag_bangkok
            else -> R.drawable.flag_msc
        }
}