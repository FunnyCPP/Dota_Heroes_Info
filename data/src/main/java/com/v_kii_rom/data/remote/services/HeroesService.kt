package com.v_kii_rom.data.remote.services

import com.v_kii_rom.data.remote.models.HeroApi
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HeroesService {
    @GET("./heroes")
    fun getHeroes(): Deferred<List<HeroApi>>
}