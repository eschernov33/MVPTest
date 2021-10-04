package com.evgenii.sbercities.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.ListAdapter
import com.evgenii.sbercities.databinding.ItemCityBinding
import com.evgenii.sbercities.presentation.model.CityItem
import com.evgenii.sbercities.presentation.viewholders.CityListViewHolder

class CityListAdapter(
    private val onCityItemClicked: (city: CityItem, extras: FragmentNavigator.Extras) -> Unit,
    private val onCityFavoriteClicked: (city: CityItem) -> Unit,
) : ListAdapter<CityItem, CityListViewHolder>(CityItemDiffCallback()) {

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
        holder.bind(city, onCityItemClicked, onCityFavoriteClicked)
    }
}