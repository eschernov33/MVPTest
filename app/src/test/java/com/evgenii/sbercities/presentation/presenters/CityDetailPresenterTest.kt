package com.evgenii.sbercities.presentation.presenters

import com.evgenii.sbercities.R
import com.evgenii.sbercities.domain.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.presenters.models.CityDetailFactory
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
    private val repository: CityListRepository = mock(CityListRepository::class.java)
    private val cityUseCase = CityUseCase(repository)
    private val cityDetailFactory = CityDetailFactory()

    @Before
    fun setUp() {
        presenter = CityDetailPresenter(cityDetailView, cityUseCase)
        `when`(repository.getCityById(CityDetailFactory.CITY_ID_NOT_FAVORITE)).thenAnswer {
            cityDetailFactory.cityIsNotFavorite
        }
        `when`(repository.getCityById(CityDetailFactory.CITY_ID_FAVORITE)).thenAnswer {
            cityDetailFactory.cityIsFavorite
        }
    }

    @Test
    fun `init when called then set correct city values`() {
        presenter.init(CityDetailFactory.CITY_ID_NOT_FAVORITE)
        verify(cityDetailView).setCityValues(cityDetailFactory.cityItemIsNotFavorite)
    }

    @Test
    fun `init when called then set correct toolbar title`() {
        presenter.init(CityDetailFactory.CITY_ID_NOT_FAVORITE)
        verify(cityDetailView).setToolbar(cityDetailFactory.cityItemIsNotFavorite.cityName)
    }

    @Test
    fun `onFavoriteClick when click to enable favorite then cityUseCase correct update`() {
        presenter.init(CityDetailFactory.CITY_ID_NOT_FAVORITE)
        presenter.onFavoriteClick()
        Assert.assertTrue(
            cityUseCase.getCityById(CityDetailFactory.CITY_ID_NOT_FAVORITE)
                .isFavorite
        )
    }

    @Test
    fun `onFavoriteClick when click to disable favorite then cityUseCase correct update`() {
        presenter.init(CityDetailFactory.CITY_ID_FAVORITE)
        presenter.onFavoriteClick()
        Assert.assertFalse(
            cityUseCase.getCityById(CityDetailFactory.CITY_ID_FAVORITE)
                .isFavorite
        )
    }

    @Test
    fun `onFavoriteClick when click to enable favorite then set enabled image`() {
        presenter.init(CityDetailFactory.CITY_ID_NOT_FAVORITE)
        presenter.onFavoriteClick()
        verify(cityDetailView).setFavoriteButton(R.drawable.ic_favorite_enable)
    }

    @Test
    fun `onFavoriteClick when click to disable favorite then set disabled image`() {
        presenter.init(cityDetailFactory.cityIsFavorite.cityId)
        presenter.onFavoriteClick()
        verify(cityDetailView).setFavoriteButton(R.drawable.ic_favorite_disable)
    }

    @Test
    fun `onActionBarBackButtonPressed when click then navigate to back screen`() {
        presenter.onActionBarBackButtonPressed()
        verify(cityDetailView).navigateToBackScreen()
    }

}