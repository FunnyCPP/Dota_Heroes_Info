package com.v_kii_rom.dota_counterpicks.activities


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.v_kii_rom.domain.models.Ability
import com.v_kii_rom.dota_counterpicks.R
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.adapters.AbilityAdapter
import com.v_kii_rom.dota_counterpicks.adapters.HeroAdapter
import com.v_kii_rom.dota_counterpicks.presenters.AbilityListPresenter
import com.v_kii_rom.dota_counterpicks.presenters.HeroListPresenter
import com.v_kii_rom.dota_counterpicks.views.AbilityListView
import com.v_kii_rom.dota_counterpicks.views.HeroListView
import kotlinx.android.synthetic.main.activity_hero__info.*
import kotlinx.android.synthetic.main.cell_ability.*
import kotlinx.android.synthetic.main.fragment_hero_list.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class Hero_Info:  MvpAppCompatActivity(),HeroListView, AbilityListView {
    private var  mAdapter = AbilityAdapter(){ item ->
    };
    var id:Int=0;
    private val heroListPresenter by moxyPresenter { HeroListPresenter() }
    private val abilityListPresenter by moxyPresenter { AbilityListPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero__info)

        val localId:Int= intent.getIntExtra("id",0)
        id=localId
        setupAdapter()
        heroListPresenter.fetchHeroes()



    }
    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerAbilityList.layoutManager = layoutManager
        recyclerAbilityList.adapter = mAdapter
    }
    fun setData(position: Int, mHeroList: List<Hero>){

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
        var s:String=""
        var i:Int=0
        txtName.text =mHeroList[position].localized_name
        Glide.with(applicationContext).load("http://cdn.dota2.com"+mHeroList[position].img).into(imgAva);
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_str-eac64b6191e66b593d7f1ac81bb262afed6565794d8f9014d66b0cbc99fa3d01.png").into(imgStrength);
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_agi-693306f455235ff5628c3429a80f2dc7e7795c013c540832dbba61ab691a73b5.png").into(imgAgi);
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_int-76ea2af3bdf60a1c92d82a1fc0845d47a071cfacfca111aa2d5e43fbae01b580.png").into(imgInt);
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_attack.png").into(imgAtt);
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_defense.png").into(imgArm);
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_speed.png").into(imgSpeed);
       while(i< mHeroList[position].roles.size-1)
        {
            s += mHeroList[position].roles[i] + ", "
            txtRoles.text=s
            i++
        }
        s+= mHeroList[position].roles[mHeroList[position].roles.size-1]
        txtRoles.text=s
        txtStrength.text= (mHeroList[position].base_str.toString()+" +  "+mHeroList[position].str_gain.toString())
        txtAgi.text= (mHeroList[position].base_agi.toString()+" +  "+mHeroList[position].agi_gain.toString())
        txtInt.text= (mHeroList[position].base_int.toString()+" +  "+mHeroList[position].int_gain.toString())
        txtAtt.text= (mHeroList[position].base_attack_min.toString()+" -  "+mHeroList[position].base_attack_max.toString())
        txtArm.text= mHeroList[position].base_armor.toString()
        txtSpeed.text= mHeroList[position].move_speed.toString()
    }


    override fun presentHeroes(data: List<Hero>) {

        if(id<=23){setData(id-1,data)}
        else if(id>=119 && id<126){setData(id-6,data)}
        else if(id==126){setData(id-10,data)}
        else if(id>126){setData(id-11,data)}
        else{setData(id-2,data)}
    }

    override fun presentLoading() {


    }


    override fun presentAbilities(data: List<Ability>) {
        mAdapter.setData(newAbilities = data)
        Toast.makeText(applicationContext,data[3].dName, Toast.LENGTH_LONG).show()
    }

    override fun presentLoadingAbility() {
        TODO("Not yet implemented")
    }


}