package com.oleksiisoroka.compouse.domain.use_case.get_coins

import com.oleksiisoroka.compouse.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoins()
}