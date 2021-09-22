package com.evgenii.sbercities.presentation.fragments

import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepositoryImpl
import com.evgenii.sbercities.databinding.FragmentCityListFavoritesBinding
import com.evgenii.sbercities.presentation.adapters.CityListAdapter
import com.evgenii.sbercities.presentation.contracts.CityListFavoriteContract
import com.evgenii.sbercities.presentation.model.CityView
import com.evgenii.sbercities.presentation.presenters.CityListFavoritePresenter

class CityListFavoriteFragment :
    CityListBaseFragment<FragmentCityListFavoritesBinding>
        (FragmentCityListFavoritesBinding::inflate) {

    override fun getToolbar(): Toolbar {
        val toolbar = binding.toolbarCityListFavorite.toolbar
        toolbar.inflateMenu(R.menu.city_list_favorite_menu)
        toolbar.setTitle(R.string.favorites)
        toolbar.setNavigationIcon(R.drawable.ic_back_button)
        toolbar.setNavigationOnClickListener {
            (presenter as CityListFavoriteContract.Presenter).onActionBarBackButtonPressed(
                navController)
        }
        return toolbar
    }

    override fun getPresenter(repository: CityListRepositoryImpl) =
        CityListFavoritePresenter(this, repository, requireContext())

    override fun initCityListAdapter(adapter: CityListAdapter) {
        binding.rvCityList.adapter = adapter
    }

    override fun getCityListView(): RecyclerView =
        binding.rvCityList

    override fun updateCityList(cityList: List<CityView>) {
        super.updateCityList(cityList)
        binding.rvCityList.isVisible = cityList.isNotEmpty()
        binding.tvEmptyList.isVisible = cityList.isEmpty()
    }
}