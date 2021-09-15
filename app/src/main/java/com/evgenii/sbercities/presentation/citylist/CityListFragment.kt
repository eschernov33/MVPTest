package com.evgenii.sbercities.presentation.citylist

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.evgenii.sbercities.App
import com.evgenii.sbercities.R
import com.evgenii.sbercities.databinding.FragmentCityListBinding
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListFragment : Fragment(), CityListContract.View {

    private lateinit var cityListPresenter: CityListContract.Presenter

    private val adapter by lazy {
        CityListAdapter(
            requireContext(),
            cityListPresenter::onCitySelected
        )
    }

    private lateinit var searchView: SearchView

    private var _binding: FragmentCityListBinding? = null
    private val binding: FragmentCityListBinding
        get() = _binding ?: throw RuntimeException("FragmentCityListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        initPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.city_list_menu, menu)
        initMenuSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menuBtnFavorite -> {
                findNavController().navigate(
                    CityListFragmentDirections.actionCityListFragmentToCityListFavoritesFragment())
                true
            }
            else -> false
        }

    private fun initMenuSearchView(menu: Menu) {
        val menuItem = menu.findItem(R.id.menuBtnSearch)
        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                cityListPresenter.onFilterApply(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityListPresenter.onFilterApply(newText)
                return false
            }
        })
    }

    private fun initPresenter() {
        val app = requireContext().applicationContext as App
        cityListPresenter = CityListPresenter(this, app.repository)
        cityListPresenter.init()
    }

    private fun setActionBar() {
        val toolbar = binding.toolbarCityList.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.setTitle(R.string.app_name)
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setHomeButtonEnabled(false)
        }
    }

    override fun showCityList(cityList: List<City>) {
        binding.rvCityList.adapter = adapter
        setAnimSharedTransition()
        setFavoriteButtonListener()
        updateCityList(cityList)
    }

    private fun setFavoriteButtonListener() {
        adapter.onCityFavoriteButtonClickListener = { city: City ->
            cityListPresenter.onFavoriteClick(city, searchView.query.toString())
        }
    }

    override fun updateCityList(cityList: List<City>) {
        adapter.submitList(cityList)
    }

    private fun setAnimSharedTransition() {
        postponeEnterTransition()
        binding.rvCityList.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun showCityDetailInfo(city: City, extras: FragmentNavigator.Extras) {
        findNavController().navigate(
            CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(city), extras
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}