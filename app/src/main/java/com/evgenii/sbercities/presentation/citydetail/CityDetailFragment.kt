package com.evgenii.sbercities.presentation.citydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.evgenii.sbercities.databinding.FragmentCityDetailBinding
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.mvp.CityDetailContract

class CityDetailFragment : Fragment(), CityDetailContract.View {

    private val city by lazy {
        getCityFromArgumentsOrException()
    }
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
        setActionBar()
        setAnimationParam()
        initPresenter()
    }

    private fun setAnimationParam() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        ViewCompat.setTransitionName(
            binding.imgCity,
            city.getUniqueTransitionName()
        )
    }

    private fun setActionBar() {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            it.title = city.cityName
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
    }

    private fun initPresenter() {
        presenter = CityDetailPresenter(this, requireContext())
        presenter.init(city)
    }

    private fun getCityFromArgumentsOrException(): City {
        return requireArguments().getParcelable(EXTRA_KEY_CITY)
            ?: throw java.lang.RuntimeException("City is not contains in arguments")
    }

    override fun setHeaderImage(resId: Int) {
        binding.imgCity.setImageResource(resId)
    }

    override fun setCityName(cityName: String) {
        binding.tvCityName.text = cityName
    }

    override fun setCountryName(countryName: String) {
        binding.tvCountryName.text = countryName
    }

    override fun setPopulation(population: String) {
        binding.tvPopulation.text = population
    }

    override fun setSquare(square: String) {
        binding.tvSquare.text = square
    }

    override fun setAltitude(altitude: String) {
        binding.tvAltitude.text = altitude
    }

    override fun setDescription(description: String) {
        binding.tvDescription.text = description
    }

    companion object {
        const val EXTRA_KEY_CITY = "city"
    }
}