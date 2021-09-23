package com.evgenii.sbercities.presentation.fragments

import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepositoryImpl
import com.evgenii.sbercities.databinding.FragmentCityListBinding
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.adapters.CityListAdapter
import com.evgenii.sbercities.presentation.contracts.CityListContract
import com.evgenii.sbercities.presentation.mapper.CityMapper
import com.evgenii.sbercities.presentation.presenters.CityListPresenter

class CityListFragment :
    CityListBaseFragment<FragmentCityListBinding, CityListPresenter>(FragmentCityListBinding::inflate),
    CityListContract.View {

    override fun getPresenter(repository: CityListRepositoryImpl): CityListPresenter {
        val cityUseCase = CityUseCase(repository)
        return CityListPresenter(this, CityMapper, cityUseCase)
    }

    override fun getToolbar(): Toolbar {
        val toolbar = binding.toolbarCityList.toolbar
        toolbar.inflateMenu(R.menu.city_list_menu)
        toolbar.setTitle(R.string.app_name)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuBtnFavorite -> {
                    presenter.onClickButtonToFavoritesScreen()
                    true
                }
                else -> false
            }
        }
        return toolbar
    }

    override fun initCityListAdapter(adapter: CityListAdapter) {
        binding.rvCityList.adapter = adapter
    }

    override fun getCityListView(): RecyclerView =
        binding.rvCityList

    override fun navigateToFavoritesScreen() {
        navController.navigate(CityListFragmentDirections
            .actionCityListFragmentToCityListFavoritesFragment())
    }

    override fun navigateToCityDetail(cityId: Int, extras: FragmentNavigator.Extras) {
        navController.navigate(CityListFragmentDirections
            .actionCityListFragmentToCityDetailFragment(cityId), extras)
    }
}