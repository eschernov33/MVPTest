package com.evgenii.sbercities.presentation.presenters

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.domain.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListFavoriteContract
import com.evgenii.sbercities.presentation.presenters.models.CityListFavoriteFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CityListFavoritePresenterTest {

    private lateinit var presenter: CityListFavoriteContract.Presenter
    private val cityListView = mock(CityListFavoriteContract.View::class.java)
    private val repository = mock(CityListRepository::class.java)
    private val extras: FragmentNavigator.Extras = mock(FragmentNavigator.Extras::class.java)
    private val cityUseCase = CityUseCase(repository)
    private val cityListFavoriteFactory = CityListFavoriteFactory()

    @Before
    fun setUp() {
        presenter = CityListFavoritePresenter(cityListView, cityUseCase)
        setRepositoryAnswers()
    }

    private fun setRepositoryAnswers() {
        whenever(repository.getCities()).thenAnswer {
            cityListFavoriteFactory.cityList
        }
        whenever(repository.getCityById(CityListFavoriteFactory.CITY_MOSCOW_ID)).thenAnswer {
            cityListFavoriteFactory.cityMoscow
        }
    }

    @Test
    fun `init when create presenter then showCityList`() {
        presenter = CityListFavoritePresenter(cityListView, cityUseCase)
        verify(cityListView).showCityList(cityListFavoriteFactory.cityItemList)
    }

    @Test
    fun `onFavoriteClick when click favorite by id then change favorite state`() {
        presenter.onFavoriteClick(CityListFavoriteFactory.CITY_MOSCOW_ID)
        val result = cityUseCase.getCityById(CityListFavoriteFactory.CITY_MOSCOW_ID)
        Assert.assertFalse(result.isFavorite)
    }

    @Test
    fun `onFavoriteClick when click favorite by id then remove from list`() {
        presenter.onFavoriteClick(CityListFavoriteFactory.CITY_MOSCOW_ID)
        val result = cityUseCase.getCities(isFavorite = true)
        Assert.assertEquals(result, cityListFavoriteFactory.cityListWithoutNotFavorite)
    }

    @Test
    fun `onFilterApply when query = ma then update receive correct result`() {
        presenter.onFilterApply(CityListFavoriteFactory.FILTER_QUERY_MADRID)
        verify(cityListView).updateCityList(cityListFavoriteFactory.cityListItemQueryMA)
    }

    @Test
    fun `onFilterApply when query is not contains in city name then update receive empty list`() {
        presenter.onFilterApply(CityListFavoriteFactory.FILTER_QUERY_NOT_CONTAINS)
        verify(cityListView).updateCityList(emptyList())
    }

    @Test
    fun `onFilterApply when query is empty then update receive all cities`() {
        presenter.onFilterApply(CityListFavoriteFactory.FILTER_QUERY_EMPTY)
        verify(cityListView).updateCityList(cityListFavoriteFactory.cityItemList)
    }

    @Test
    fun `onCitySelected when click on city item then navigate to detail`() {
        presenter.onCitySelected(CityListFavoriteFactory.CITY_MOSCOW_ID, extras)
        verify(cityListView).navigateToCityDetail(CityListFavoriteFactory.CITY_MOSCOW_ID, extras)
    }

    @Test
    fun `onActionBarBackButtonPressed when click then navigate to back screen`() {
        presenter.onActionBarBackButtonPressed()
        verify(cityListView).navigateToBackScreen()
    }
}