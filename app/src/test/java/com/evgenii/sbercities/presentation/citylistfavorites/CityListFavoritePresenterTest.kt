package com.evgenii.sbercities.presentation.citylistfavorites

import android.view.View
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.CityListRepository
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CityListFavoritePresenterTest {

    private lateinit var cityListView: CityListContract.View
    private lateinit var repository: CityListRepository
    private lateinit var presenter: CityListFavoritePresenter
    private val moscow = City(
        CITY_MOSCOW_ID,
        CITY_MOSCOW_NAME,
        CITY_MOSCOW_COUNTRY_NAME,
        CITY_MOSCOW_POPULATION,
        CITY_MOSCOW_SQUARE,
        CITY_MOSCOW_IMG_ICON_RES_ID,
        CITY_MOSCOW_IMG_CARD_RES_ID,
        CITY_MOSCOW_DESCRIPTION,
        CITY_MOSCOW_ALTITUDE,
        CITY_MOSCOW_IS_FAVORITE
    )

    private val madrid = City(
        CITY_MADRID_ID,
        CITY_MADRID_NAME,
        CITY_MADRID_COUNTRY_NAME,
        CITY_MADRID_POPULATION,
        CITY_MADRID_SQUARE,
        CITY_MADRID_IMG_ICON_RES_ID,
        CITY_MADRID_IMG_CARD_RES_ID,
        CITY_MADRID_DESCRIPTION,
        CITY_MADRID_ALTITUDE,
        CITY_MADRID_IS_FAVORITE
    )

    private val cityList: List<City> = listOf(moscow, madrid)

    @Before
    fun setUp() {
        cityListView = Mockito.mock(CityListContract.View::class.java)
        repository = Mockito.mock(CityListRepository::class.java)
        presenter = CityListFavoritePresenter(cityListView, repository)
        setRepositoryAnswers()
    }

    private fun setRepositoryAnswers() {
        whenever(repository.getCities(isFavorite = true)).thenAnswer {
            cityList.filter(City::isFavorite)
        }

        whenever(repository.getCities(any(), eq(true))).thenAnswer {
            var filteredListCity = cityList
            filteredListCity = filteredListCity.filter(City::isFavorite)
            if (it.arguments.isNotEmpty() && it.arguments[0] != null) {
                val query = it.arguments[0] as String
                if (query.isNotEmpty()) {
                    filteredListCity = filteredListCity.filter { city ->
                        city.cityName.contains(query, true) ||
                                city.countryName.contains(query, true)
                    }
                }
            }
            filteredListCity
        }
    }

    @Test
    fun `should show all city after init`() {
        presenter.init()
        val result = cityList.filter(City::isFavorite)
        verify(cityListView).showCityList(result)
    }

    @Test
    fun `should show all city if filter is null`() {
        presenter.onFilterApply(null)
        val result = cityList.filter(City::isFavorite)
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `should show all city if filter is empty`() {
        presenter.onFilterApply("")
        val result = cityList.filter(City::isFavorite)
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `should show list filtered by query ma`() {
        val result = cityList.filter(City::isFavorite).filter {
            it.cityName.contains(FILTER_QUERY_MADRID, true)
                    || it.countryName.contains(FILTER_QUERY_MADRID, true)
        }
        presenter.onFilterApply(FILTER_QUERY_MADRID)
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `should show list filtered by uppercase query m`() {
        val result = cityList.filter(City::isFavorite).filter {
            it.cityName.contains(FILTER_QUERY_M_UPPERCASE, true)
                    || it.countryName.contains(FILTER_QUERY_M_UPPERCASE, true)
        }
        presenter.onFilterApply(FILTER_QUERY_M_UPPERCASE)
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `should show empty list by not contains query`() {
        val result = cityList.filter(City::isFavorite).filter {
            it.cityName.contains(FILTER_QUERY_NOT_CONTAINS_CHARS, true)
                    || it.countryName.contains(FILTER_QUERY_NOT_CONTAINS_CHARS, true)
        }
        presenter.onFilterApply(FILTER_QUERY_NOT_CONTAINS_CHARS)
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `should call function open detail info with correct param`() {
        val view: View = Mockito.mock(View::class.java)
        presenter.onCitySelected(moscow, view)
        verify(cityListView).showCityDetailInfo(eq(moscow), any())
    }

    @Test
    fun `should set correct favorite after click on favorite button`() {
        val cityUpdate = moscow.copy(isFavorite = !moscow.isFavorite)
        presenter.onFavoriteClick(moscow)
        verify(repository).updateCity(cityUpdate)
    }

    @Test
    fun `should show list filtered by query ma after changed isFavorite param`() {
        val result = cityList.filter(City::isFavorite).filter {
            it.cityName.contains(FILTER_QUERY_MADRID, true)
                    || it.countryName.contains(FILTER_QUERY_MADRID, true)
        }
        presenter.onFavoriteClick(moscow, FILTER_QUERY_MADRID)
        verify(cityListView).updateCityList(result)
    }

    companion object {
        const val CITY_MOSCOW_ID = 1
        const val CITY_MOSCOW_NAME = "Moscow"
        const val CITY_MOSCOW_COUNTRY_NAME = "Russia"
        const val CITY_MOSCOW_POPULATION = 800_000
        const val CITY_MOSCOW_SQUARE = 200
        const val CITY_MOSCOW_IMG_ICON_RES_ID = R.drawable.flag_msc
        const val CITY_MOSCOW_IMG_CARD_RES_ID = R.drawable.city_moscow
        const val CITY_MOSCOW_DESCRIPTION = "Some description"
        const val CITY_MOSCOW_ALTITUDE = 200
        const val CITY_MOSCOW_IS_FAVORITE = false

        const val CITY_MADRID_ID = 2
        const val CITY_MADRID_NAME = "Madrid"
        const val CITY_MADRID_COUNTRY_NAME = "Spain"
        const val CITY_MADRID_POPULATION = 100_000
        const val CITY_MADRID_SQUARE = 300
        const val CITY_MADRID_IMG_ICON_RES_ID = R.drawable.flag_madrid
        const val CITY_MADRID_IMG_CARD_RES_ID = R.drawable.city_madrid
        const val CITY_MADRID_DESCRIPTION = "Some description"
        const val CITY_MADRID_ALTITUDE = 300
        const val CITY_MADRID_IS_FAVORITE = true

        const val FILTER_QUERY_MADRID = "ma"
        const val FILTER_QUERY_M_UPPERCASE = "M"
        const val FILTER_QUERY_NOT_CONTAINS_CHARS = "Lc"
    }
}