package com.evgenii.sbercities.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evgenii.sbercities.presentation.model.CityParam

class CityItemDiffCallback : DiffUtil.ItemCallback<CityParam>() {

    override fun areItemsTheSame(oldItem: CityParam, newItem: CityParam): Boolean {
        return oldItem.cityId == newItem.cityId
    }

    override fun areContentsTheSame(oldItem: CityParam, newItem: CityParam): Boolean {
        return oldItem == newItem
    }
}