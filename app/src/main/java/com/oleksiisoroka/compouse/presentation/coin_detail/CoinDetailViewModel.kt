package com.oleksiisoroka.compouse.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleksiisoroka.compouse.common.Constants
import com.oleksiisoroka.compouse.common.Resource
import com.oleksiisoroka.compouse.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsSate())
    val state: State<CoinDetailsSate> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            _state.value = when (result) {
                is Resource.Error -> CoinDetailsSate(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> CoinDetailsSate(isLoading = true)
                is Resource.Success -> CoinDetailsSate(coin = result.data)
            }
        }.launchIn(viewModelScope)
    }
}