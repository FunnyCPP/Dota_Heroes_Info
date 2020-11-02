package com.v_kii_rom.dota_counterpicks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.v_kii_rom.domain.models.Ability
import com.v_kii_rom.dota_counterpicks.R
import java.util.*

class AbilityAdapter: RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {
    private var mHeroList: MutableList<Ability> = LinkedList()
    fun setData(newHeroes: List<Ability>)
    {
        mHeroList.clear()
        mHeroList.addAll(newHeroes)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.cell_ability,
                viewGroup,
                false
            )
        )

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mHeroList[position])
    }

    override fun getItemCount(): Int {
        return  mHeroList.count()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val txtCooldown: TextView = itemView.findViewById(R.id.txtCooldown)
        private val txtManacost: TextView = itemView.findViewById(R.id.txtManacost)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        private val imgAbility: ImageView = itemView.findViewById(R.id.imgAbility)
        private val imgCooldown: ImageView = itemView.findViewById(R.id.imgCooldawn)
        private val  imgManacost: ImageView= itemView.findViewById(R.id.ImgManacost)


        fun bind(model: Ability) {
            txtTitle.text = model.name
            Glide.with(itemView).load("https://raw.githubusercontent.com/kriskate/dota-data/master/assets/images/abilities/"+model.tag+".png").into(imgAbility)
            if(model.cooldown=="")
                imgCooldown.visibility=View.GONE
            if(model.manacost=="")
                imgManacost.visibility=View.GONE
            Glide.with(itemView).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/tooltips/cooldown.png").into(imgCooldown)
            Glide.with(itemView).load("https://cdn.cloudflare.steamstatic.com/apps/dota2/images/tooltips/mana.png").into(imgManacost)
            txtCooldown.text=model.cooldown
            txtManacost.text=model.manacost
            txtDescription.text=model.description





        }
    }
}