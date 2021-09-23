package com.evgenii.sbercities.presentation.fragments

import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepositoryImpl
import com.evgenii.sbercities.databinding.FragmentCityListFavoritesBinding
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.adapters.CityListAdapter
import com.evgenii.sbercities.presentation.contracts.CityListFavoriteContract
import com.evgenii.sbercities.presentation.mapper.CityMapper
import com.evgenii.sbercities.presentation.model.CityParam
import com.evgenii.sbercities.presentation.presenters.CityListFavoritePresenter

class CityListFavoriteFragment :
    CityListBaseFragment<FragmentCityListFavoritesBinding, CityListFavoritePresenter>
        (FragmentCityListFavoritesBinding::inflate), CityListFavoriteContract.View {

    override fun getPresenter(repository: CityListRepositoryImpl): CityListFavoritePresenter {
        val cityUseCase = CityUseCase(repository)
        return CityListFavoritePresenter(this, CityMapper, cityUseCase)
    }

    override fun getToolbar(): Toolbar {
        val toolbar = binding.toolbarCityListFavorite.toolbar
        toolbar.inflateMenu(R.menu.city_list_favorite_menu)
        toolbar.setTitle(R.string.favorites)
        toolbar.setNavigationIcon(R.drawable.ic_back_button)
        toolbar.setNavigationOnClickListener {
            presenter.onActionBarBackButtonPressed()
        }
        return toolbar
    }

    override fun initCityListAdapter(adapter: CityListAdapter) {
        binding.rvCityList.adapter = adapter
    }

    override fun getCityListView(): RecyclerView =
        binding.rvCityList

    override fun updateCityList(cityList: List<CityParam>) {
        super.updateCityList(cityList)
        binding.rvCityList.isVisible = cityList.isNotEmpty()
        binding.tvEmptyList.isVisible = cityList.isEmpty()
    }

    override fun navigateToBackScreen() {
        navController.popBackStack()
    }

    override fun navigateToCityDetail(cityId: Int, extras: FragmentNavigator.Extras) {
        navController.navigate(CityListFavoriteFragmentDirections.actionCityListFavoritesFragmentToCityDetailFragment(
            cityId),
            extras)
    }
}