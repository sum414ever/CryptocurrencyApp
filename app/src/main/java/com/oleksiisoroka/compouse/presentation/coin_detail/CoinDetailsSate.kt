package com.oleksiisoroka.compouse.presentation.coin_detail

import com.oleksiisoroka.compouse.domain.model.CoinDetail

data class CoinDetailsSate(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
