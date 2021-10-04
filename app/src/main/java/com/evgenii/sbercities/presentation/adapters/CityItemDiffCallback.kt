package com.evgenii.sbercities.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evgenii.sbercities.presentation.model.CityItem

class CityItemDiffCallback : DiffUtil.ItemCallback<CityItem>() {

    override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean =
        oldItem.cityId == newItem.cityId

    override fun areContentsTheSame(oldItem: CityItem, newItem: CityItem): Boolean =
        oldItem == newItem

}