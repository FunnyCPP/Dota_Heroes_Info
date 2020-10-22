package com.v_kii_rom.data.remote.providers

import com.v_kii_rom.data.remote.helpers.RetrofitFactory
import com.v_kii_rom.data.remote.models.HeroApi
import kotlinx.coroutines.Deferred

class HeroProviderImpl {

    fun getHeroesList(): Deferred<List<HeroApi>> {
        return RetrofitFactory.getHeroesService().getHeroes()
    }

}