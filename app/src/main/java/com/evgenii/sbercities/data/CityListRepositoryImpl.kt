package com.evgenii.sbercities.data

import android.content.Context
import com.evgenii.sbercities.R
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListRepositoryImpl(context: Context) : CityListContract.Repository {

    private val listCity: List<City> = listOf(
        City(
            context.getString(R.string.city_moscow),
            context.getString(R.string.country_russia),
            POPULATION_MOSCOW,
            SQUARE_MOSCOW,
            description = context.getString(R.string.moscow_description),
            imgCityCard = R.drawable.city_moscow,
            imgIcon = R.drawable.flag_msc,
            altitude = ALTITUDE_MOSCOW,
        ),
        City(
            context.getString(R.string.city_amsterdam),
            context.getString(R.string.country_netherlands),
            POPULATION_AMSTERDAM,
            SQUARE_AMSTERDAM,
            description = context.getString(R.string.amsterdam_description),
            imgCityCard = R.drawable.city_amsterdam,
            imgIcon = R.drawable.flag_amsterdam,
            altitude = ALTITUDE_AMSTERDAM
        ),
        City(
            context.getString(R.string.city_bangkok),
            context.getString(R.string.country_thailand),
            POPULATION_BANGKOK,
            SQUARE_BANGKOK,
            description = context.getString(R.string.bangkok_description),
            imgCityCard = R.drawable.city_bangkok,
            imgIcon = R.drawable.flag_bangkok,
            altitude = ALTITUDE_BANGKOK
        ),
        City(
            context.getString(R.string.city_madrid),
            context.getString(R.string.country_spain),
            POPULATION_MADRID,
            SQUARE_MADRID,
            description = context.getString(R.string.madrid_description),
            imgCityCard = R.drawable.city_madrid,
            imgIcon = R.drawable.flag_madrid,
            altitude = ALTITUDE_MADRID
        ),
        City(
            context.getString(R.string.city_new_york),
            context.getString(R.string.country_usa),
            POPULATION_NEW_YORK,
            SQUARE_NEW_YORK,
            description = context.getString(R.string.new_york_description),
            imgCityCard = R.drawable.city_new_york,
            imgIcon = R.drawable.flag_new_york,
            altitude = ALTITUDE_NEW_YORK
        ),
        City(
            context.getString(R.string.city_paris),
            context.getString(R.string.country_france),
            POPULATION_PARIS,
            SQUARE_PARIS,
            description = context.getString(R.string.paris_description),
            imgCityCard = R.drawable.city_paris,
            imgIcon = R.drawable.flag_paris,
            altitude = ALTITUDE_PARIS
        )
    )

    override fun loadCities(): List<City> {
        return listCity
    }

    companion object {
        private const val POPULATION_MOSCOW = 11_920_000
        private const val POPULATION_PARIS = 2_161_000
        private const val POPULATION_NEW_YORK = 8_419_000
        private const val POPULATION_MADRID = 3_223_000
        private const val POPULATION_AMSTERDAM = 821_752
        private const val POPULATION_BANGKOK = 5_676_000

        private const val SQUARE_MOSCOW = 2511
        private const val SQUARE_PARIS = 105
        private const val SQUARE_NEW_YORK = 784
        private const val SQUARE_MADRID = 604
        private const val SQUARE_AMSTERDAM = 219
        private const val SQUARE_BANGKOK = 1569

        private const val ALTITUDE_MOSCOW = 130
        private const val ALTITUDE_PARIS = 35
        private const val ALTITUDE_NEW_YORK = 10
        private const val ALTITUDE_MADRID = 667
        private const val ALTITUDE_AMSTERDAM = 5
        private const val ALTITUDE_BANGKOK = 2
    }
}