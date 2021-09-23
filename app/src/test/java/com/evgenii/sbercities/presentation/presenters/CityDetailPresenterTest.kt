package com.evgenii.sbercities.presentation.presenters

import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.domain.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.mapper.CityMapper
import com.evgenii.sbercities.presentation.model.CityParam
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class CityDetailPresenterTest {

    private lateinit var presenter: CityDetailContract.Presenter
    private val cityDetailView: CityDetailContract.View = mock(CityDetailContract.View::class.java)
    private val mapper = CityMapper
    private val repository: CityListRepository = mock(CityListRepository::class.java)
    private val cityUseCase = CityUseCase(repository)

    private val cityParam: CityParam = CityParam(
        CITY_ID,
        CITY_NAME,
        CITY_COUNTRY_NAME,
        CITY_POPULATION,
        CITY_SQUARE,
        R.drawable.flag_msc,
        R.drawable.city_moscow,
        CITY_DESCRIPTION,
        CITY_ALTITUDE,
        R.drawable.ic_favorite_disable
    )

    private val city: City = City(
        CITY_ID,
        CITY_NAME,
        CITY_COUNTRY_NAME,
        CITY_POPULATION,
        CITY_SQUARE,
        CITY_DESCRIPTION,
        CITY_ALTITUDE,
        CityType.MOSCOW,
        CITY_IS_FAVORITE
    )

    @Before
    fun setUp() {
        presenter = CityDetailPresenter(cityDetailView, mapper, cityUseCase)
        `when`(repository.getCityById(CITY_ID)).thenAnswer { city }
    }

    @Test
    fun `test init when called then set city values`() {
        presenter.init(CITY_ID)
        verify(cityDetailView).setCityValues(cityParam)
    }

    @Test
    fun `test usecase when get city by id then get city moscow`() {
        val cityResult = cityUseCase.getCityById(CITY_ID)
        Assert.assertEquals(cityResult, city)
    }

    @Test
    fun `test mapper when map entity then get cityParam`() {
        val cityResult = mapper.mapFromEntity(city)
        Assert.assertEquals(cityResult, cityParam)
    }

    @Test
    fun `test init when called then set toolbar`() {
        presenter.init(CITY_ID)
        verify(cityDetailView).setToolbar(cityParam.cityName)
    }

    @Test
    fun `test favorite when button click then set favorite button enable`() {
        presenter.init(CITY_ID)
        presenter.onFavoriteClick()
        verify(cityDetailView).setFavoriteButton(R.drawable.ic_favorite_enable)
    }

    @Test
    fun `test back button when click then navigate to back screen`() {
        presenter.onActionBarBackButtonPressed()
        verify(cityDetailView).navigateToBackScreen()
    }

    companion object {
        const val CITY_ID = 1
        const val CITY_NAME = "Moscow"
        const val CITY_COUNTRY_NAME = "Russia"
        const val CITY_POPULATION = 800_000
        const val CITY_SQUARE = 200
        const val CITY_DESCRIPTION = "Some description"
        const val CITY_ALTITUDE = 200
        const val CITY_IS_FAVORITE = false
    }
}