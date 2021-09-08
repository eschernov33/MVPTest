package com.evgenii.sbercities.presentation.citylist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.evgenii.sbercities.databinding.FragmentCityListBinding
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityListContract
import com.evgenii.sbercities.presentation.App

class CityListFragment : Fragment(), CityListContract.View {

    private val cityListPresenter = CityListPresenter(this, App.cityModel)

    private var _binding: FragmentCityListBinding? = null
    private val binding: FragmentCityListBinding
        get() = _binding ?: throw RuntimeException("FragmentCityListBinding == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cityListPresenter.onAttach(context)
    }

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
        cityListPresenter.onViewCreated()
    }

    override fun showCityList(cityList: List<City>) {
        val adapter = CityListAdapter(cityList, requireContext())
        adapter.onCityItemClickListener = {
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