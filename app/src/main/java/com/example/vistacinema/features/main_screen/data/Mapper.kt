package com.example.vistacinema.features.main_screen.data.api

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