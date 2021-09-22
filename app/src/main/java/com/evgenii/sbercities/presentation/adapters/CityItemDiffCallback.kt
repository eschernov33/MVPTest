package com.evgenii.sbercities.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evgenii.sbercities.presentation.model.CityView

class CityItemDiffCallback : DiffUtil.ItemCallback<CityView>() {

    override fun areItemsTheSame(oldItem: CityView, newItem: CityView): Boolean {
        return oldItem.cityId == newItem.cityId
    }

    override fun areContentsTheSame(oldItem: CityView, newItem: CityView): Boolean {
        return oldItem == newItem
    }
}