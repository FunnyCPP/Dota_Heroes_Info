package com.v_kii_rom.dota_counterpicks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
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
        private var i=0
        var s:String =""

        fun bind(model: Hero) {
            txtTitle.text = model.localized_name
            while(i< model.roles.size)
            {
                s += model.roles[i] + " "
                txtAttackType.text=s
                i++
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

                        if (row.localized_name.toLowerCase()
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

