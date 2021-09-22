package com.evgenii.sbercities.presentation.viewholders

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.databinding.ItemCityBinding
import com.evgenii.sbercities.presentation.model.CityView
import com.evgenii.sbercities.presentation.utils.AnimationUtils

class CityListViewHolder(private val binding: ItemCityBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        city: CityView,
        onCityItemClicked: (city: CityView, view: View) -> Unit,
        onCityFavoriteClicked: (city: CityView) -> Unit,
    ) {
        ViewCompat.setTransitionName(binding.ivCityCard,
            AnimationUtils.getUniqueTransitionName(city.cityId))
        with(binding) {
            tvCityName.text = city.cityName
            tvCountryName.text = city.countryName
            tvPopulation.text = city.population
            tvSquare.text = city.square
            ivIconCity.setImageResource(city.imgIconResId)
            ivCityCard.setImageResource(city.imgCityCardResId)
            imgBtnIsFavorite.setImageResource(city.favoriteImg)
            imgBtnIsFavorite.setOnClickListener {
                onCityFavoriteClicked(city)
            }
            root.setOnClickListener {
                onCityItemClicked(city, ivCityCard)
            }
        }
    }
}