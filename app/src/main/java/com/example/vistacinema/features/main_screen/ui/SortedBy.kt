package com.example.vistacinema.features.main_screen.ui

sealed class SortedBy : Sorting {
    object Date : Sorting
    object Name : Sorting
    object Popularity : Sorting
    object Rating : Sorting
}
