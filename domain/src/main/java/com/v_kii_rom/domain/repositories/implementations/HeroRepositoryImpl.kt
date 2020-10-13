package com.v_kii_rom.domain.repositories.implementations

import com.v_kii_rom.data.remote.providers.HeroProviderImpl
import com.v_kii_rom.domain.converters.HeroConverterImpl
import com.v_kii_rom.domain.models.Hero
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class HeroRepositoryImpl( private val heroConverter: HeroConverterImpl) {

    private val heroProvider: HeroProviderImpl  = HeroProviderImpl()
    suspend fun fetchHeroes(): Deferred<List<Hero>> {
      return try{
          val   heroes = heroProvider.getHeroesList().await()
           GlobalScope.async {

               heroes.map { hero -> heroConverter.fromApiToUI(model = hero) }
           }
      } catch (e: Exception){
            GlobalScope.async { error(e) }
      }
    }
}