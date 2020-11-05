package com.v_kii_rom.dota_counterpicks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.R
import java.util.*

class HeroAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    private var mHeroList: MutableList<Hero> = LinkedList()
    private var editText: String = ""

    fun setData(newHeroes: List<Hero>, localEditText: String) {
        val filteredList: MutableList<Hero> = LinkedList()
        editText = localEditText
        mHeroList.clear()

        for(row in newHeroes){

            if (row.name.toLowerCase(Locale.ROOT).startsWith(editText.toLowerCase(Locale.ROOT))
            ) {
                filteredList.add(row)
            }
        }

        for(row in newHeroes){

            if ((row.name.toLowerCase(Locale.ROOT).contains(editText.toLowerCase(Locale.ROOT)) ||
                row.attributes.Role.toLowerCase(Locale.ROOT).contains(editText.toLowerCase(Locale.ROOT)) )&& !row.name.toLowerCase(Locale.ROOT).startsWith(editText.toLowerCase(Locale.ROOT) )
            ) {
                filteredList.add(row)
            }

        }

        mHeroList.addAll(filteredList)
        if(localEditText=="")
            mHeroList= mHeroList.sortedBy { it.name }.toMutableList()
    notifyDataSetChanged()
}


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.cell_hero,
                viewGroup,
                false
            )
        )

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
                viewHolder.bind(model = mHeroList[position])
                viewHolder.itemView.setOnClickListener { listener(mHeroList[position].tag) }

    }

    override fun getItemCount(): Int {
        return  mHeroList.count()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtHeroTitle)
        private val roles: TextView = itemView.findViewById(R.id.txtHeroAttackType)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)


        fun bind(model: Hero) {
            txtTitle.text = model.name
            Glide.with(itemView).load("https://raw.githubusercontent.com/FunnyCPP/Dota-data/develop/assets/images/heroes/icons/"+model.tag+".png").into(imgAvatar)
            var i=0
            var s=""
            val rolesList: List<String>
            rolesList=rolesToListOfRoles(model.attributes.Role)
            while (i<rolesList.size)
            {
                s+= rolesList[i]+" "
                i++
            }
            roles.text=s

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

}

