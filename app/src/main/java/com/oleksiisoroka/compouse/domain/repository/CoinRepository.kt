package com.oleksiisoroka.compouse.domain.repository

import com.oleksiisoroka.compouse.common.Resource
import com.oleksiisoroka.compouse.domain.model.Coin
import com.oleksiisoroka.compouse.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<List<Coin>>>

    fun getCoinById(coinId: String): Flow<Resource<CoinDetail>>
}