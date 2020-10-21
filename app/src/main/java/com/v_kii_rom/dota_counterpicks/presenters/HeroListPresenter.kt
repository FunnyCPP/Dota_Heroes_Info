package com.v_kii_rom.dota_counterpicks.presenters

import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.views.HeroListView
import android.util.Log
import com.v_kii_rom.domain.converters.AbilityConverterImpl
import com.v_kii_rom.domain.converters.HeroConverterImpl
import com.v_kii_rom.domain.repositories.implementations.AbilityRepositoryImpl
import com.v_kii_rom.domain.repositories.implementations.HeroRepositoryImpl
import com.v_kii_rom.dota_counterpicks.views.AbilityListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import java.lang.Exception


class HeroListPresenter: MvpPresenter<HeroListView>() {

    private val heroRepositoryImpl = HeroRepositoryImpl(heroConverter = HeroConverterImpl())
    private val abilityRepositoryImpl = AbilityRepositoryImpl(abilityConverter = AbilityConverterImpl())

    fun fetchHeroes() {
        viewState.presentLoading()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val heroes = heroRepositoryImpl.fetchHeroes().await()
                withContext(Dispatchers.Main) {
                    viewState.presentHeroes(data = heroes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val abilities = abilityRepositoryImpl.fetchAbilities().await()
                withContext(Dispatchers.Main) {
                    viewState.presentAbilities(data = abilities)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}