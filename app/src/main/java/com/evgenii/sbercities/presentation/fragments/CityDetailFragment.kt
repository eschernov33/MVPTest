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
import com.evgenii.sbercities.domain.usecases.CityUseCase
import com.evgenii.sbercities.presentation.contracts.CityDetailContract
import com.evgenii.sbercities.presentation.model.CityItem
import com.evgenii.sbercities.presentation.presenters.CityDetailPresenter
import com.evgenii.sbercities.presentation.utils.AnimationUtils

class CityDetailFragment : Fragment(), CityDetailContract.View {

    private lateinit var presenter: CityDetailContract.Presenter

    private val args by navArgs<CityDetailFragmentArgs>()

    private val cityId by lazy { args.city }

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

    override fun setToolbar(title: String) {
        val toolbar = binding.toolbarCityDetail
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_back_button_dark)
        toolbar.setNavigationOnClickListener {
            presenter.onActionBarBackButtonPressed()
        }
    }

    override fun setFavoriteButton(imgRes: Int) {
        binding.fabFavorite.setImageResource(imgRes)
    }

    override fun setCityValues(city: CityItem) {
        with(binding) {
            imgCity.setImageResource(city.imgCityCardResId)
            tvCityName.text = city.cityName
            tvCountryName.text = city.countryName
            tvPopulation.text = resources.getQuantityString(
                R.plurals.population_field,
                city.population,
                city.population
            )
            tvSquare.text = resources.getString(
                R.string.square_field, city.square
            )
            tvAltitude.text = resources.getString(R.string.altitude_field, city.altitude)
            tvDescription.text = city.description
            fabFavorite.setImageResource(city.favoriteImg)
        }
    }

    override fun navigateToBackScreen() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initPresenter() {
        val app = requireContext().applicationContext as App
        val cityUseCase = CityUseCase(app.repository)
        presenter = CityDetailPresenter(this, cityUseCase)
        presenter.init(cityId)
    }

    private fun setFavoriteButtonListener() {
        binding.fabFavorite.setOnClickListener {
            presenter.onFavoriteClick()
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
}