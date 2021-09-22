package com.evgenii.sbercities.presentation.presenters

import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepository
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract
import com.evgenii.sbercities.presentation.contracts.CityListContract
import com.evgenii.sbercities.presentation.fragments.CityListFragmentDirections
import com.evgenii.sbercities.presentation.model.CityView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CityListPresenterTest {

    private lateinit var cityListView: CityListBaseContract.View
    private lateinit var repository: CityListRepository
    private lateinit var presenter: CityListContract.Presenter

    private val extras: FragmentNavigator.Extras = mock(FragmentNavigator.Extras::class.java)
    private val navController: NavController = mock(NavController::class.java)

    private val cityList = listOf(
        City(
            CITY_MOSCOW_ID,
            CITY_MOSCOW_NAME,
            CITY_MOSCOW_COUNTRY_NAME,
            CITY_MOSCOW_POPULATION,
            CITY_MOSCOW_SQUARE,
            CITY_MOSCOW_DESCRIPTION,
            CITY_MOSCOW_ALTITUDE,
            CityType.MOSCOW,
            CITY_MOSCOW_IS_FAVORITE
        ),
        City(
            CITY_MADRID_ID,
            CITY_MADRID_NAME,
            CITY_MADRID_COUNTRY_NAME,
            CITY_MADRID_POPULATION,
            CITY_MADRID_SQUARE,
            CITY_MADRID_DESCRIPTION,
            CITY_MADRID_ALTITUDE,
            CityType.MADRID,
            CITY_MADRID_IS_FAVORITE
        )
    )

    private val cityViewList = listOf(
        CityView(
            CITY_MOSCOW_ID,
            CITY_MOSCOW_NAME,
            CITY_MOSCOW_COUNTRY_NAME,
            "",
            "",
            R.drawable.flag_msc,
            R.drawable.city_moscow,
            CITY_MOSCOW_DESCRIPTION,
            "",
            R.drawable.ic_favorite_disable
        ),
        CityView(
            CITY_MADRID_ID,
            CITY_MADRID_NAME,
            CITY_MADRID_COUNTRY_NAME,
            "",
            "",
            R.drawable.flag_madrid,
            R.drawable.city_madrid,
            CITY_MADRID_DESCRIPTION,
            "",
            R.drawable.ic_favorite_disable
        )
    )

    @Before
    fun setUp() {
        cityListView = mock(CityListBaseContract.View::class.java)
        repository = mock(CityListRepository::class.java)
        presenter = CityListPresenter(cityListView, repository, null)
        setRepositoryAnswers()
    }

    private fun setRepositoryAnswers() {
        whenever(repository.getCities()).thenAnswer {
            cityList
        }
        whenever(repository.getCityById(CityDetailPresenterTest.CITY_ID)).thenAnswer {
            cityList.find { it.cityId == CITY_MOSCOW_ID }
        }
    }

    @Test
    fun `test update list when change favorite param then update city list`() {
        presenter.onFavoriteClick(CITY_MOSCOW_ID)
        val result = cityViewList.toList()
        val cityResult = result.find { cityView ->
            cityView.cityId == CITY_MOSCOW_ID
        }
        cityResult?.favoriteImg = R.drawable.ic_favorite_enable
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `test filter when filter apply ma then filter result madrid`() {
        presenter.onFilterApply(FILTER_QUERY_MADRID)
        val result = cityViewList.filter { cityView ->
            cityView.cityName.contains(FILTER_QUERY_MADRID, true)
        }
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `test filter when filter apply not contains query then filter result all cities`() {
        presenter.onFilterApply(FILTER_QUERY_NOT_CONTAINS)
        val result = cityViewList.filter { cityView ->
            cityView.cityName.contains(FILTER_QUERY_NOT_CONTAINS, true)
        }
        verify(cityListView).updateCityList(result)
    }

    @Test
    fun `test filter when filter apply empty query then filter result all cities`() {
        presenter.onFilterApply(FILTER_QUERY_EMPTY)
        verify(cityListView).updateCityList(cityViewList)
    }

    @Test
    fun `test open detail when click on city item then navigate to detail`() {
        presenter.onCitySelected(CITY_MOSCOW_ID, extras, navController)
        verify(navController).navigate(CityListFragmentDirections
            .actionCityListFragmentToCityDetailFragment(CITY_MOSCOW_ID), extras)
    }

    @Test
    fun `test open favorites when click on favorite button then navigate to favorites`() {
        presenter.onClickButtonToFavoritesScreen(navController)
        verify(navController).navigate(CityListFragmentDirections
            .actionCityListFragmentToCityListFavoritesFragment())
    }

    companion object {
        const val CITY_MOSCOW_ID = 1
        const val CITY_MOSCOW_NAME = "Moscow"
        const val CITY_MOSCOW_COUNTRY_NAME = "Russia"
        const val CITY_MOSCOW_POPULATION = 800_000
        const val CITY_MOSCOW_SQUARE = 200
        const val CITY_MOSCOW_DESCRIPTION = "Some description"
        const val CITY_MOSCOW_ALTITUDE = 200
        const val CITY_MOSCOW_IS_FAVORITE = false

        const val CITY_MADRID_ID = 2
        const val CITY_MADRID_NAME = "Madrid"
        const val CITY_MADRID_COUNTRY_NAME = "Spain"
        const val CITY_MADRID_POPULATION = 100_000
        const val CITY_MADRID_SQUARE = 300
        const val CITY_MADRID_DESCRIPTION = "Some description"
        const val CITY_MADRID_ALTITUDE = 300
        const val CITY_MADRID_IS_FAVORITE = false

        const val FILTER_QUERY_MADRID = "ma"
        const val FILTER_QUERY_EMPTY = ""
        const val FILTER_QUERY_NOT_CONTAINS = "Lc"
    }
}