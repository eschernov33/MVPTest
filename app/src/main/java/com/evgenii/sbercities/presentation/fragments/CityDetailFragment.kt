package com.evgenii.sbercities.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.evgenii.sbercities.App
import com.evgenii.sbercities.R
import com.evgenii.sbercities.databinding.FragmentCityDetailBinding
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.model.CityView
import com.evgenii.sbercities.presentation.presenters.CityDetailPresenter
import com.evgenii.sbercities.presentation.utils.AnimationUtils

class CityDetailFragment : Fragment(), CityDetailContract.View {

    private val args by navArgs<CityDetailFragmentArgs>()

    private val cityId by lazy { args.city }

    private val navController by lazy { findNavController() }

    private lateinit var presenter: CityDetailContract.Presenter

    private var _binding: FragmentCityDetailBinding? = null
    private val binding: FragmentCityDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCityDetailBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAnimationParam()
        initPresenter()
        setFavoriteButtonListener()
    }

    private fun initPresenter() {
        val app = requireContext().applicationContext as App
        presenter = CityDetailPresenter(this, app.repository, requireContext())
        presenter.init(cityId)
    }

    private fun setFavoriteButtonListener() {
        binding.fabFavorite.setOnClickListener {
            presenter.onFavoriteClick(cityId)
        }
    }

    private fun setAnimationParam() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        ViewCompat.setTransitionName(
            binding.imgCity,
            AnimationUtils.getUniqueTransitionName(cityId)
        )
    }

    override fun setActionBar(title: String) {
        val toolbar = binding.toolbarCityDetail
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_back_button_dark)
        toolbar.setNavigationOnClickListener {
            presenter.onActionBarBackButtonPressed(navController)
        }
    }

    override fun setCityValues(city: CityView) {
        with(binding) {
            imgCity.setImageResource(city.imgCityCardResId)
            tvCityName.text = city.cityName
            tvCountryName.text = city.countryName
            tvPopulation.text = city.population
            tvSquare.text = city.square
            tvAltitude.text = city.altitude
            tvDescription.text = city.description
            fabFavorite.setImageResource(city.favoriteImg)
        }
    }

    override fun setFavoriteButton(imgRes: Int) {
        binding.fabFavorite.setImageResource(imgRes)
    }
}