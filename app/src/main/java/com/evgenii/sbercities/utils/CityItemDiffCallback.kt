package com.evgenii.sbercities.utils

import androidx.recyclerview.widget.DiffUtil
import com.evgenii.sbercities.models.City

class CityItemDiffCallback : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.cityId == newItem.cityId
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}