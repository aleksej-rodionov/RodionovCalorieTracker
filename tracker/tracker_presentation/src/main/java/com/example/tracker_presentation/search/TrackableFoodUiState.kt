package com.example.tracker_presentation.search

import com.example.tracker_domain.model.TrackableFood

data class TrackableFoodUiState( // ui model of TrackableFood
    val food: TrackableFood,
    val isExpanded: Boolean = false, // so that not pollute domain=layer model with those parameters
    val amount: String = "" // so that not pollute domain=layer model with those ui parameters
)
