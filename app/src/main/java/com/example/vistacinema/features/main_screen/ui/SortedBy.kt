package com.example.vistacinema.features.main_screen.ui

import com.example.vistacinema.features.base.view_model.Sorting

sealed class SortedBy : Sorting {
    object Date : Sorting
    object Name : Sorting
    object Popularity : Sorting
    object Rating : Sorting
}
