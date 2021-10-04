package com.evgenii.sbercities.presentation.presenters

import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.domain.repository.CityListRepository
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityListContract
import com.evgenii.sbercities.presentation.presenters.models.CityListFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CityListPresenterTest {

    private lateinit var presenter: CityListContract.Presenter
    private val extras: FragmentNavigator.Extras = mock(FragmentNavigator.Extras::class.java)
    private val cityListView = mock(CityListContract.View::class.java)
    private val repository = mock(CityListRepository::class.java)
    private val cityUseCase = CityUseCase(repository)
    private val cityListFactory = CityListFactory()

    @Before
    fun setUp() {
        presenter = CityListPresenter(cityListView, cityUseCase)
        setRepositoryAnswers()
    }

    private fun setRepositoryAnswers() {
        whenever(repository.getCities()).thenAnswer {
            cityListFactory.cityList
        }
        whenever(repository.getCityById(CityListFactory.CITY_MOSCOW_ID)).thenAnswer {
            cityListFactory.cityMoscow
        }
    }

    @Test
    fun `init when create presenter then showCityList`() {
        presenter = CityListPresenter(cityListView, cityUseCase)
        verify(cityListView).showCityList(cityListFactory.cityItemList)
    }

    @Test
    fun `onFavoriteClick when click favorite by id then change favorite state`() {
        presenter.onFavoriteClick(CityListFactory.CITY_MOSCOW_ID)
        val result = cityUseCase.getCityById(CityListFactory.CITY_MOSCOW_ID)
        Assert.assertFalse(result.isFavorite)
    }

    @Test
    fun `onFilterApply when query = ma then update receive correct result`() {
        presenter.onFilterApply(CityListFactory.FILTER_QUERY_MADRID)
        verify(cityListView).updateCityList(cityListFactory.cityListItemQueryMA)
    }

    @Test
    fun `onFilterApply when query is not contains in city name then update receive empty list`() {
        presenter.onFilterApply(CityListFactory.FILTER_QUERY_NOT_CONTAINS)
        verify(cityListView).updateCityList(emptyList())
    }

    @Test
    fun `onFilterApply when query is empty then update receive all cities`() {
        presenter.onFilterApply(CityListFactory.FILTER_QUERY_EMPTY)
        verify(cityListView).updateCityList(cityListFactory.cityItemList)
    }

    @Test
    fun `onCitySelected when click on city item then navigate to detail`() {
        presenter.onCitySelected(CityListFactory.CITY_MOSCOW_ID, extras)
        verify(cityListView).navigateToCityDetail(CityListFactory.CITY_MOSCOW_ID, extras)
    }

    @Test
    fun `onClickButtonToFavoritesScreen when click on favorite button then navigate to favorites`() {
        presenter.onClickButtonToFavoritesScreen()
        verify(cityListView).navigateToFavoritesScreen()
    }
}