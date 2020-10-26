package com.v_kii_rom.dota_counterpicks.views


import com.v_kii_rom.domain.models.Hero
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
interface HeroListView: MvpView {
    @AddToEndSingle
    fun presentHeroes(data: List<Hero>)
    @AddToEndSingle
    fun presentLoading()
}