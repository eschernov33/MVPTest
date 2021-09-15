package com.evgenii.sbercities.presentation.citylistfavorites

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.evgenii.sbercities.App
import com.evgenii.sbercities.R
import com.evgenii.sbercities.databinding.FragmentCityListFavoritesBinding
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract
import com.evgenii.sbercities.presentation.citylist.CityListAdapter

class CityListFavoritesFragment : Fragment(), CityListContract.View {

    private lateinit var cityListPresenter: CityListContract.Presenter

    private val adapter by lazy {
        CityListAdapter(
            requireContext(),
            cityListPresenter::onCitySelected
        )
    }

    private var _binding: FragmentCityListFavoritesBinding? = null
    private val binding: FragmentCityListFavoritesBinding
        get() = _binding ?: throw RuntimeException("FragmentCityListFavoritesBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCityListFavoritesBinding.inflate(inflater, container, false)
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
        inflater.inflate(R.menu.city_list_favorite_menu, menu)
        initMenuSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initMenuSearchView(menu: Menu) {
        val menuItem = menu.findItem(R.id.menuBtnSearch)
        val searchView = menuItem.actionView as SearchView
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
        cityListPresenter = CityListFavoritePresenter(this, app.repository)
        cityListPresenter.init()
    }

    private fun setActionBar() {
        val toolbar = binding.toolbarCityListFavorite.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.setTitle(R.string.favorites)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }
    }

    override fun showCityList(cityList: List<City>) {
        binding.rvCityList.adapter = adapter
        setAnimSharedTransition()
        adapter.onCityFavoriteButtonClickListener = { city: City ->
            cityListPresenter.onFavoriteClick(city)
        }
        updateCityList(cityList)
    }

    override fun updateCityList(cityList: List<City>) {
        adapter.submitList(cityList)
        if (cityList.isEmpty()) {
            binding.rvCityList.visibility = View.GONE
            binding.tvEmptyList.visibility = View.VISIBLE
        } else {
            binding.rvCityList.visibility = View.VISIBLE
            binding.tvEmptyList.visibility = View.GONE
        }
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
            CityListFavoritesFragmentDirections
                .actionCityListFavoritesFragmentToCityDetailFragment(city),
            extras
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}