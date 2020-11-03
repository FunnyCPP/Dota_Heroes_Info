package com.v_kii_rom.dota_counterpicks.activities


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.v_kii_rom.dota_counterpicks.R
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.adapters.AbilityAdapter
import com.v_kii_rom.dota_counterpicks.presenters.HeroListPresenter
import com.v_kii_rom.dota_counterpicks.views.HeroListView
import kotlinx.android.synthetic.main.activity_hero__info.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class HeroInfo:  MvpAppCompatActivity(),HeroListView {
    private var tag=""
    private val heroListPresenter by moxyPresenter { HeroListPresenter() }
    private val mAdapter=  AbilityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero__info)

        val localTag= intent.getStringExtra("id")
        tag=localTag.toString()
        setupAdapter()
        heroListPresenter.fetchHeroes()



    }
    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerAbilityList.layoutManager = layoutManager
        recyclerAbilityList.adapter = mAdapter

    }
    @SuppressLint("SetTextI18n")
    private fun setData(position: Int, mHeroList: List<Hero>){

        val txtName =findViewById<TextView>(R.id.text_name)
        val txtRoles =findViewById<TextView>(R.id.text_roles)
        val imgAva=findViewById<ImageView>(R.id.img_ava)
        val imgStrength=findViewById<ImageView>(R.id.img_strength)
        val txtStrength=findViewById<TextView>(R.id.text_strength)
        val txtAgi=findViewById<TextView>(R.id.txtAgi)
        val txtInt=findViewById<TextView>(R.id.txtInt)
        val txtAtt=findViewById<TextView>(R.id.txtAtt)
        val txtArm=findViewById<TextView>(R.id.txtArm)
        val txtSpeed=findViewById<TextView>(R.id.txtSpeed)
        val imgAgi=findViewById<ImageView>(R.id.imgAgi)
        val imgInt=findViewById<ImageView>(R.id.imgInt)
        val imgAtt=findViewById<ImageView>(R.id.imgAtt)
        val imgArm=findViewById<ImageView>(R.id.imgArm)
        val imgSpeed=findViewById<ImageView>(R.id.imgSpeed)
        val txtHealth=findViewById<TextView>(R.id.txtHealth)
        val txtMana=findViewById<TextView>(R.id.txtMana)
        txtName.text =mHeroList[position].name
        Glide.with(applicationContext).load("https://raw.githubusercontent.com/kriskate/dota-data/master/assets/images/heroes/small/"+mHeroList[position].tag+".png").into(imgAva)
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_str-eac64b6191e66b593d7f1ac81bb262afed6565794d8f9014d66b0cbc99fa3d01.png").into(imgStrength)
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_agi-693306f455235ff5628c3429a80f2dc7e7795c013c540832dbba61ab691a73b5.png").into(imgAgi)
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_int-76ea2af3bdf60a1c92d82a1fc0845d47a071cfacfca111aa2d5e43fbae01b580.png").into(imgInt)
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_attack.png").into(imgAtt)
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_defense.png").into(imgArm)
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_speed.png").into(imgSpeed)
        txtRoles.text=mHeroList[position].attributes.Role
        txtStrength.text= (mHeroList[position].attributes.AttributeBaseStrength.toString()+" +  "+mHeroList[position].attributes.AttributeStrengthGain.toString())
        txtAgi.text= (mHeroList[position].attributes.AttributeBaseAgility.toString()+" +  "+mHeroList[position].attributes.AttributeAgilityGain.toString())
        txtInt.text= (mHeroList[position].attributes.AttributeBaseIntelligence.toString()+" +  "+mHeroList[position].attributes.AttributeIntelligenceGain.toString())
        txtAtt.text= (mHeroList[position].attributes.AttackDamageMin.toString()+" -  "+mHeroList[position].attributes.AttackDamageMax.toString())
        txtArm.text= mHeroList[position].attributes.ArmorPhysical.toString()
        txtSpeed.text= mHeroList[position].attributes.MovementSpeed.toString()
        txtHealth.text=mHeroList[position].attributes.StatusHealth.toString()+" + "+mHeroList[position].attributes.StatusHealthRegen.toString()
        txtMana.text=mHeroList[position].attributes.StatusMana.toString()+" + "+mHeroList[position].attributes.StatusManaRegen.toString()

    }


    override fun presentHeroes(data: List<Hero>) {
        var position=0
        var i=0
        for(item in data)
        {
            if(item.tag==tag)
            {
                position=i
            }
            i++
        }
        setData(position,data)
        mAdapter.setData(data[position].abilities)
        relativeLayoutAbillities.visibility=View.VISIBLE
        recyclerAbilityList.visibility = View.VISIBLE
        progressBar1.visibility = View.GONE
    }

    override fun presentLoading() {

    }


}