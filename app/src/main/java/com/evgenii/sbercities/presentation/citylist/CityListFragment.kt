package com.evgenii.sbercities.presentation.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.evgenii.sbercities.App
import com.evgenii.sbercities.databinding.FragmentCityListBinding
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract

class CityListFragment : Fragment(), CityListContract.View {

    private lateinit var cityListPresenter: CityListContract.Presenter
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
        val app = requireContext().applicationContext as App
        cityListPresenter = CityListPresenter(this, app.cityModel, app.repository)
        cityListPresenter.onViewCreated()
    }

    override fun showCityList(cityList: List<City>) {
        val adapter = CityListAdapter(cityList, requireContext()) {
            cityListPresenter.onCitySelected(it)
        }
        binding.rvCityList.adapter = adapter
    }

    override fun showCityDetailInfo(city: City) {
        findNavController().navigate(
            CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(city)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}