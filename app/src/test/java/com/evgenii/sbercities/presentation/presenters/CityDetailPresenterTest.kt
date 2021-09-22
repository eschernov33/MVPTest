package com.evgenii.sbercities.presentation.presenters

import androidx.navigation.NavController
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepository
import com.evgenii.sbercities.domain.model.City
import com.evgenii.sbercities.domain.model.CityType
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.model.CityView
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
    private val navController: NavController = mock(NavController::class.java)

    private val cityView: CityView = CityView(
        CITY_ID,
        CITY_NAME,
        CITY_COUNTRY_NAME,
        "",
        "",
        R.drawable.flag_msc,
        R.drawable.city_moscow,
        CITY_DESCRIPTION,
        "",
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
        presenter = CityDetailPresenter(cityDetailView, repository, null)
        `when`(repository.getCityById(CITY_ID)).thenAnswer { city }
    }

    @Test
    fun `test init when called then set city values`() {
        presenter.init(CITY_ID)
        verify(cityDetailView).setCityValues(cityView)
    }

    @Test
    fun `test init when called then set action bar`() {
        presenter.init(CITY_ID)
        verify(cityDetailView).setActionBar(cityView.cityName)
    }

    @Test
    fun `test favorite when button click then set favorite button enable`() {
        presenter.onFavoriteClick(CITY_ID)
        verify(cityDetailView).setFavoriteButton(R.drawable.ic_favorite_enable)
    }

    @Test
    fun `test back button when click then call popBackStack`() {
        presenter.onActionBarBackButtonPressed(navController)
        verify(navController).popBackStack()
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