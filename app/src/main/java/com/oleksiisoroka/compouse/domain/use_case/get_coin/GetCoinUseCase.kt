package com.oleksiisoroka.compouse.domain.use_case.get_coin

import com.oleksiisoroka.compouse.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String) = repository.getCoinById(coinId)
}