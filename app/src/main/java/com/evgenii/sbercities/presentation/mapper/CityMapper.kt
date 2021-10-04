package com.evgenii.sbercities.presentation.mapper

import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.presentation.model.CityItem

class CityMapper {

    fun mapFromEntity(item: City): CityItem =
        CityItem(
            item.cityId,
            item.cityName,
            item.countryName,
            item.population,
            item.square,
            getIcon(item.cityType),
            getImage(item.cityType),
            item.description,
            item.altitude,
            getFavoriteImg(item.isFavorite)
        )

    fun mapFromListEntity(list: List<City>): List<CityItem> =
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