package com.example.sdk.other

sealed class Either<out L, out R> {

    data class Error<out L>(val error: L) : Either<L, Nothing>()
    data class Data<out R>(val data: R) : Either<Nothing, R>()

    fun <L> error(a: L) = Either.Error(a)
    fun <R> data(b: R) = Either.Data(b)
}