package com.example.vistacinema.features.main_screen.data.api

import com.example.vistacinema.features.base.utils.toCalendar
import com.example.vistacinema.features.main_screen.data.api.model.MainModel
import com.example.vistacinema.features.main_screen.data.api.model.MoveModel
import com.example.vistacinema.features.main_screen.domain.model.Main
import com.example.vistacinema.features.main_screen.domain.model.Movie

fun MoveModel.toDomain() = Movie(
    adult = adult,
    genres = genres.map { it.name },
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    releaseDate = releaseDate.toCalendar(),
    posterPath = posterPath,
    popularity = popularity,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    favourite = false
)

fun MainModel.toDomain() = Main(
    page = page,
    results = results.map { it.toDomain() },
    totalPages = totalPages,
    totalResults = totalResults
)