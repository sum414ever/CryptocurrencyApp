package com.oleksiisoroka.compouse.data.remote

import com.oleksiisoroka.compouse.data.dto.CoinDetailDto
import com.oleksiisoroka.compouse.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto
}