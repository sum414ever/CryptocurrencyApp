package com.oleksiisoroka.compouse.presentation.coin_list

import com.oleksiisoroka.compouse.domain.model.Coin

data class CoinListSate(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
