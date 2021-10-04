package com.evgenii.sbercities.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.evgenii.sbercities.App
import com.evgenii.sbercities.R
import com.evgenii.sbercities.data.repository.CityListRepositoryImpl
import com.evgenii.sbercities.presentation.adapters.CityListAdapter
import com.evgenii.sbercities.presentation.contracts.CityListBaseContract
import com.evgenii.sbercities.presentation.model.CityItem

abstract class CityListBaseFragment<VB : ViewBinding, PRESENTER : CityListBaseContract.Presenter>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment(), CityListBaseContract.View {

    protected lateinit var presenter: PRESENTER

    protected val navController: NavController by lazy { findNavController() }

    private val preDrawListener = {
        startPostponedEnterTransition()
        true
    }

    private val adapter by lazy {
        CityListAdapter({ city, extras ->
            presenter.onCitySelected(city.cityId, extras)
        })
        { city -> presenter.onFavoriteClick(city.cityId) }
    }

    private var _binding: VB? = null
    val binding: VB
        get() = _binding ?: throw RuntimeException("FragmentCityListBinding is null")

    abstract fun initCityListAdapter(adapter: CityListAdapter)
    abstract fun getPresenter(repository: CityListRepositoryImpl): PRESENTER
    abstract fun getCityListView(): RecyclerView
    abstract fun getToolbar(): Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        initPresenter()
    }

    override fun showCityList(cityList: List<CityItem>) {
        initCityListAdapter(adapter)
        setAnimSharedTransition()
        updateCityList(cityList)
    }

    override fun updateCityList(cityList: List<CityItem>) =
        adapter.submitList(cityList)

    override fun onDestroyView() {
        super.onDestroyView()
        getCityListView().viewTreeObserver.removeOnPreDrawListener(preDrawListener)
        _binding = null
    }

    private fun initPresenter() {
        val app = requireContext().applicationContext as App
        val repository = app.repository
        presenter = getPresenter(repository)
    }

    private fun setActionBar() {
        val toolbar = getToolbar()
        initMenuSearchView(toolbar.menu)
    }

    private fun initMenuSearchView(menu: Menu) {
        val menuItem = menu.findItem(R.id.menuBtnSearch)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onFilterApply(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.onFilterApply(newText)
                return false
            }
        })
    }

    private fun getTransitionExtras(view: View, transitionId: String) =
        FragmentNavigatorExtras(view to transitionId)

    private fun setAnimSharedTransition() {
        postponeEnterTransition()
        val rvCityList = getCityListView()
        rvCityList.viewTreeObserver.addOnPreDrawListener(preDrawListener)
    }
}