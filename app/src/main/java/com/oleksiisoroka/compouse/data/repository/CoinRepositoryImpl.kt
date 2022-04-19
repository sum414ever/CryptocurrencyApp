package com.oleksiisoroka.compouse.data.repository

import com.oleksiisoroka.compouse.common.Resource
import com.oleksiisoroka.compouse.data.dto.toCoin
import com.oleksiisoroka.compouse.data.dto.toCoinDetail
import com.oleksiisoroka.compouse.data.remote.CoinPaprikaApi
import com.oleksiisoroka.compouse.domain.model.Coin
import com.oleksiisoroka.compouse.domain.model.CoinDetail
import com.oleksiisoroka.compouse.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = api.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit((Resource.Error<List<Coin>>("Couldn't reach server. Search your internet connection")))
        }
    }

    override fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = api.getCoin(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit((Resource.Error<CoinDetail>("Couldn't reach server. Search your internet connection")))
        }
    }
}