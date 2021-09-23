package com.evgenii.sbercities.presentation.viewholders

import android.view.View
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.R
import com.evgenii.sbercities.databinding.ItemCityBinding
import com.evgenii.sbercities.presentation.model.CityParam
import com.evgenii.sbercities.presentation.utils.AnimationUtils

class CityListViewHolder(private val binding: ItemCityBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        city: CityParam,
        onCityItemClicked: (city: CityParam, extras: FragmentNavigator.Extras) -> Unit,
        onCityFavoriteClicked: (city: CityParam) -> Unit,
    ) {
        ViewCompat.setTransitionName(binding.ivCityCard,
            AnimationUtils.getUniqueTransitionName(city.cityId))
        val context = binding.root.context
        val resources = context.resources
        with(binding) {

            tvCityName.text = city.cityName
            tvCountryName.text = city.countryName
            tvPopulation.text = resources.getQuantityString(
                R.plurals.population_field,
                city.population,
                city.population
            )
            tvSquare.text = resources.getString(R.string.square_field, city.square)
            ivIconCity.setImageResource(city.imgIconResId)
            ivCityCard.setImageResource(city.imgCityCardResId)
            imgBtnIsFavorite.setImageResource(city.favoriteImg)
            imgBtnIsFavorite.setOnClickListener {
                onCityFavoriteClicked(city)
            }
            root.setOnClickListener {
                onCityItemClicked(city, getTransitionExtras(
                    ivCityCard,
                    AnimationUtils.getUniqueTransitionName(city.cityId)
                ))
            }
        }
    }

    private fun getTransitionExtras(view: View, transitionId: String) =
        FragmentNavigatorExtras(view to transitionId)
}