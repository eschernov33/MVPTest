package com.evgenii.sbercities.presentation.citydetail

import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.CityListRepository
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityDetailContract
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CityDetailPresenterTest {

    private val cityDetailView: CityDetailContract.View = mock(CityDetailContract.View::class.java)
    private val repository: CityListRepository = mock(CityListRepository::class.java)
    private lateinit var presenter: CityDetailContract.Presenter
    private lateinit var city: City

    @Before
    fun setUp() {
        presenter = CityDetailPresenter(cityDetailView, repository)
        city = City(
            CITY_ID,
            CITY_NAME,
            CITY_COUNTRY_NAME,
            CITY_POPULATION,
            CITY_SQUARE,
            CITY_IMG_ICON_RES_ID,
            CITY_IMG_CARD_RES_ID,
            CITY_DESCRIPTION,
            CITY_ALTITUDE,
            CITY_IS_FAVORITE
        )
    }

    @Test
    fun `should set correct altitude after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setAltitude(CITY_ALTITUDE)
    }

    @Test
    fun `should set correct city name after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setCityName(CITY_NAME)
    }

    @Test
    fun `should set correct country name after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setCountryName(CITY_COUNTRY_NAME)
    }

    @Test
    fun `should set correct description after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setDescription(CITY_DESCRIPTION)
    }

    @Test
    fun `should set correct header image after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setHeaderImage(CITY_IMG_CARD_RES_ID)
    }

    @Test
    fun `should set correct population after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setPopulation(CITY_POPULATION)
    }

    @Test
    fun `should set correct square after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setSquare(CITY_SQUARE)
    }

    @Test
    fun `should set correct favorite after init with correct city`() {
        presenter.init(city)
        verify(cityDetailView).setFavoriteButton(CITY_IS_FAVORITE)
    }

    @Test
    fun `should set correct favorite after click on favorite button`() {
        val cityUpdate = city.copy(isFavorite = !city.isFavorite)
        presenter.onFavoriteClick(city)
        verify(repository).updateCity(cityUpdate)
    }

    companion object {
        const val CITY_ID = 1
        const val CITY_NAME = "Moscow"
        const val CITY_COUNTRY_NAME = "Russia"
        const val CITY_POPULATION = 800_000
        const val CITY_SQUARE = 200
        const val CITY_IMG_ICON_RES_ID = R.drawable.flag_msc
        const val CITY_IMG_CARD_RES_ID = R.drawable.city_moscow
        const val CITY_DESCRIPTION = "Some description"
        const val CITY_ALTITUDE = 200
        const val CITY_IS_FAVORITE = true
    }
}