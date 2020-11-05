package com.v_kii_rom.dota_counterpicks.activities


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.R
import com.v_kii_rom.dota_counterpicks.adapters.AbilityAdapter
import com.v_kii_rom.dota_counterpicks.presenters.HeroListPresenter
import com.v_kii_rom.dota_counterpicks.views.HeroListView
import kotlinx.android.synthetic.main.activity_hero__info.*
import kotlinx.android.synthetic.main.img_pop_up.*
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
        val layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerAbilityList.layoutManager = layoutManager
        recyclerAbilityList.adapter = mAdapter

    }
    @SuppressLint("SetTextI18n")
    private fun setData(position: Int, mHeroList: List<Hero>){
        val rolesList: List<String>
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
        var i=0
        var s=""
        txtName.text =mHeroList[position].name
        Glide.with(applicationContext).load("https://raw.githubusercontent.com/FunnyCPP/Dota-data/develop/assets/images/heroes/small/" + mHeroList[position].tag + ".png").into(
            imgAva
        )
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_str-eac64b6191e66b593d7f1ac81bb262afed6565794d8f9014d66b0cbc99fa3d01.png").into(
            imgStrength
        )
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_agi-693306f455235ff5628c3429a80f2dc7e7795c013c540832dbba61ab691a73b5.png").into(
            imgAgi
        )
        Glide.with(applicationContext).load("https://pl.dotabuff.com/assets/hero_int-76ea2af3bdf60a1c92d82a1fc0845d47a071cfacfca111aa2d5e43fbae01b580.png").into(
            imgInt
        )
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_attack.png").into(
            imgAtt
        )
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_defense.png").into(
            imgArm
        )
        Glide.with(applicationContext).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heropedia/overviewicon_speed.png").into(
            imgSpeed
        )

       rolesList=rolesToListOfRoles(mHeroList[position].attributes.Role)
        while (i<rolesList.size)
        {
            s+= rolesList[i]+" "
            i++
        }
        txtRoles.text=s
        txtStrength.text= (mHeroList[position].attributes.AttributeBaseStrength.toString()+" +  "+mHeroList[position].attributes.AttributeStrengthGain.toString())
        txtAgi.text= (mHeroList[position].attributes.AttributeBaseAgility.toString()+" +  "+mHeroList[position].attributes.AttributeAgilityGain.toString())
        txtInt.text= (mHeroList[position].attributes.AttributeBaseIntelligence.toString()+" +  "+mHeroList[position].attributes.AttributeIntelligenceGain.toString())
        txtAtt.text= (mHeroList[position].attributes.AttackDamageMin.toString()+" -  "+mHeroList[position].attributes.AttackDamageMax.toString())
        txtArm.text= mHeroList[position].attributes.ArmorPhysical.toString()
        txtSpeed.text= mHeroList[position].attributes.MovementSpeed.toString()
        txtHealth.text=mHeroList[position].attributes.StatusHealth.toString()+" + "+mHeroList[position].attributes.StatusHealthRegen.toString()
        txtMana.text=mHeroList[position].attributes.StatusMana.toString()+" + "+mHeroList[position].attributes.StatusManaRegen.toString()
        imgAva.setOnClickListener {

            // Initialize a new layout inflater instance
            val inflater: LayoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.img_pop_up, null)
            val focusable = true
            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT,
                focusable
            )

            // Set an elevation for the popup window
            popupWindow.elevation = 10.0F


            // If API level 23 or higher then execute the code
            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for popup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.TOP
            popupWindow.exitTransition = slideOut

            val  textBio= view.findViewById<TextView>(R.id.txtBio)
            val imageHeroAva=view.findViewById<ImageView>(R.id.imgHeroAva)
            textBio.text=mHeroList[position].bio
            Glide.with(applicationContext).load("https://raw.githubusercontent.com/FunnyCPP/Dota-data/develop/assets/images/heroes/vert/" + mHeroList[position].tag +"_vert" + ".jpg").into(imageHeroAva)
            // Get the widgets reference from custom view

            // Set a dismiss listener for popup window
            popupWindow.setOnDismissListener {
                Toast.makeText(applicationContext, "Popup closed", Toast.LENGTH_SHORT).show()
            }

            view.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                root_layout, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
            )
        }

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
        setData(position, data)
        mAdapter.setData(data[position].abilities)
        relativeLayoutAbillities.visibility=View.VISIBLE
        recyclerAbilityList.visibility = View.VISIBLE
        progressBar1.visibility = View.GONE
    }

    override fun presentLoading() {

    }
    fun rolesToListOfRoles(s: String): MutableList<String>
    {
        var editableRoles: String=""
        var finalRoles: MutableList<String> = mutableListOf()
        var i=0;
        while(i <s.length)
        {
            if(s[i]==',')
            {
                finalRoles.add(editableRoles)
                editableRoles=""
            }
            else
                editableRoles+=s[i]
            i++
        }
        return finalRoles
    }


}