package com.v_kii_rom.dota_counterpicks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.dota_counterpicks.R
import java.util.*

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.ViewHolder>(), Filterable {

    private var mHeroList: MutableList<Hero> = LinkedList()

    fun setData(newHeroes: List<Hero>)
    {
        mHeroList.clear()
        mHeroList.addAll(newHeroes)
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
    }

    override fun getItemCount(): Int {
        return  mHeroList.count()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txtHeroTitle)
        private val txtAttackType: TextView = itemView.findViewById(R.id.txtHeroAttackType)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)

        fun bind(model: Hero) {
            txtTitle.text = model.title
            if(model.attackType == 0)
            {
                txtAttackType.text = itemView.context.getString(R.string.attack_type_melee)
            }
            else
            {
                txtAttackType.text = itemView.context.getString(R.string.attack_type_ranged)
            }
        }
    }
   override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                } else {
                    val filteredList: MutableList<Hero> = ArrayList()
                    for (row in mHeroList) {

                        if (row.title.toLowerCase()
                                .contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    mHeroList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = mHeroList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {


                notifyDataSetChanged()
            }
        }
    }
}

