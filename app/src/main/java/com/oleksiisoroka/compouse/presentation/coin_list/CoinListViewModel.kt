package com.oleksiisoroka.compouse.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleksiisoroka.compouse.common.Resource
import com.oleksiisoroka.compouse.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListSate())
    val state: State<CoinListSate> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            _state.value = when (result) {
                is Resource.Error -> CoinListSate(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> CoinListSate(isLoading = true)
                is Resource.Success -> CoinListSate(coins = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}