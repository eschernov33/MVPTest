package com.evgenii.sbercities.presentation.citylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ListAdapter
import com.evgenii.sbercities.R
import com.evgenii.sbercities.databinding.ItemCityBinding
import com.evgenii.sbercities.models.City
import com.evgenii.sbercities.utils.CityItemDiffCallback

class CityListAdapter(
    private val context: Context,
    private val onCityItemClickListener: (city: City, view: View) -> Unit,
) : ListAdapter<City, CityListViewHolder>(CityItemDiffCallback()) {

    var onCityFavoriteButtonClickListener: ((city: City) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val binding = ItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        val city = getItem(position)
        val binding = holder.binding
        ViewCompat.setTransitionName(binding.ivCityCard, city.getUniqueTransitionName())
        with(binding) {
            tvCityName.text = city.cityName
            tvCountryName.text = city.countryName
            tvPopulation.text = context.resources.getQuantityString(
                R.plurals.population_field,
                city.population,
                city.population
            )
            tvSquare.text = context.resources.getString(R.string.square_field, city.square)
            ivIconCity.setImageResource(city.imgIconResId)
            ivCityCard.setImageResource(city.imgCityCardResId)
            setFavoriteButton(imgBtnIsFavorite, city.isFavorite)
            imgBtnIsFavorite.setOnClickListener {
                onCityFavoriteButtonClickListener?.invoke(city)
            }
            root.setOnClickListener {
                onCityItemClickListener(city, ivCityCard)
            }
        }
    }

    private fun setFavoriteButton(view: ImageButton, isFavorite: Boolean) {
        if (isFavorite)
            view.setImageResource(R.drawable.ic_favorite_enable)
        else
            view.setImageResource(R.drawable.ic_favorite_disable)
    }
}