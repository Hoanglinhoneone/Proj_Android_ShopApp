package com.shop.user.data.datasource

import kotlinx.coroutines.flow.Flow

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(message = message)
    class Loading<T> : Resource<T>()
    class Unspecified<T> : Resource<T>()
}

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: Flow<T>) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}