package com.v_kii_rom.dota_counterpicks.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.R
import com.v_kii_rom.dota_counterpicks.adapters.HeroAdapter
import com.v_kii_rom.dota_counterpicks.presenters.HeroListPresenter
import com.v_kii_rom.dota_counterpicks.views.HeroListView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity :  MvpAppCompatActivity(),HeroListView {
    private var  mAdapter = HeroAdapter { item ->
        val intent = Intent(this, HeroInfo::class.java)
        intent.putExtra("id", item)
        startActivity(intent)
    }
    private val heroListPresenter by moxyPresenter { HeroListPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        heroListPresenter.fetchHeroes()
        val editSearch: EditText=findViewById(R.id.editSearch)
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                setupAdapter()
                heroListPresenter.fetchHeroes()
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerHeroList.layoutManager = layoutManager
        recyclerHeroList.adapter = mAdapter
    }

    override fun presentHeroes(data: List<Hero>) {
        mAdapter.setData(newHeroes = data, editSearch.text.toString())
        recyclerHeroList.visibility = View.VISIBLE
        editSearch.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

    }

    override fun presentLoading() {
        recyclerHeroList.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

    }



}