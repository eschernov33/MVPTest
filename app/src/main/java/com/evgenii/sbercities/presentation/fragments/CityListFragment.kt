package com.evgenii.sbercities.presentation.fragments

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepositoryImpl
import com.evgenii.sbercities.databinding.FragmentCityListBinding
import com.evgenii.sbercities.presentation.adapters.CityListAdapter
import com.evgenii.sbercities.presentation.contracts.CityListContract
import com.evgenii.sbercities.presentation.presenters.CityListPresenter

class CityListFragment :
    CityListBaseFragment<FragmentCityListBinding>(FragmentCityListBinding::inflate) {

    override fun getToolbar(): Toolbar {
        val toolbar = binding.toolbarCityList.toolbar
        toolbar.inflateMenu(R.menu.city_list_menu)
        toolbar.setTitle(R.string.app_name)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuBtnFavorite -> {
                    (presenter as CityListContract.Presenter)
                        .onClickButtonToFavoritesScreen(navController)
                    true
                }
                else -> false
            }
        }
        return toolbar
    }

    override fun getPresenter(repository: CityListRepositoryImpl) =
        CityListPresenter(this, repository, requireContext())

    override fun initCityListAdapter(adapter: CityListAdapter) {
        binding.rvCityList.adapter = adapter
    }

    override fun getCityListView(): RecyclerView =
        binding.rvCityList
}