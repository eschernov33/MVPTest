package com.evgenii.sbercities.presentation.citylist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evgenii.sbercities.R
import com.evgenii.sbercities.databinding.ItemCityBinding
import com.evgenii.sbercities.models.City

class CityListAdapter(
    private val cityList: List<City>,
    private val context: Context,
    private val onCityItemClickListener: (city: City) -> Unit,
) : RecyclerView.Adapter<CityListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val binding = ItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        val city = cityList[position]
        val binding = holder.binding
        with(binding) {
            tvCityName.text = city.cityName
            tvCountryName.text = city.countryName
            tvPopulation.text = String.format(
                context.resources.getQuantityString(
                    R.plurals.population_field,
                    city.population,
                    city.population
                )
            )
            tvSquare.text = String.format(
                context.getString(R.string.square_field),
                city.square
            )
            ivIconCity.setImageResource(city.imgIcon)
            ivCityCard.setImageResource(city.imgCityCard)
            root.setOnClickListener {
                onCityItemClickListener.invoke(city)
            }
        }
    }

    override fun getItemCount() =
        cityList.size
}