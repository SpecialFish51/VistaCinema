package com.example.vistacinema.features.base.func

import com.example.vistacinema.features.base.func.Either

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}