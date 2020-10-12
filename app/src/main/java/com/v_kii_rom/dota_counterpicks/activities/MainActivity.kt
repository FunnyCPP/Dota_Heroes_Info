package com.v_kii_rom.dota_counterpicks.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.v_kii_rom.dota_counterpicks.R
import com.v_kii_rom.dota_counterpicks.adapters.HeroAdapter
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.presenters.HeroListPresenter
import com.v_kii_rom.dota_counterpicks.views.HeroListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hero_list.recyclerHeroList
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity :  MvpAppCompatActivity(),HeroListView {
    private var  mAdapter = HeroAdapter()


    private val heroListPresenter by moxyPresenter { HeroListPresenter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        heroListPresenter.fetchHeroes()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerHeroList.layoutManager = layoutManager
        recyclerHeroList.adapter = mAdapter
    }

    override fun presentHeroes(data: List<Hero>) {
        recyclerHeroList.visibility = View.VISIBLE
        txtHeroListLoading.visibility = View.GONE
        mAdapter.setData(newHeroes = data)
    }

    override fun presentLoading() {
        recyclerHeroList.visibility = View.VISIBLE
        txtHeroListLoading.visibility = View.VISIBLE

    }

}