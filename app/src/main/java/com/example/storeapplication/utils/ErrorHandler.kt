package com.example.storeapplication.utils

import kotlinx.coroutines.CoroutineExceptionHandler

object ErrorHandler {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()}
}