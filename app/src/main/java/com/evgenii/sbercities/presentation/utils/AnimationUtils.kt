package com.evgenii.sbercities.presentation.utils

object AnimationUtils {

    private const val TRANSITION_NAME_PREFIX = "imageHeader_"

    fun getUniqueTransitionName(cityId: Int) =
        "$TRANSITION_NAME_PREFIX$cityId"
}