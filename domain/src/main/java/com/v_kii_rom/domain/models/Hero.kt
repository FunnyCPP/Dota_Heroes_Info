package com.v_kii_rom.domain.models



data class Hero(val tag: String="",
                var name: String="",
                val bio: String="",
                val hype: String="",
                val abilities: List<Ability>,
    // val talents: List<TalentsApi>,
                val attributes: Attribut,
                val abilities_aghs:List<Ability>,
                val abilities_special: List<Ability>,
                val abilities_hidden: List<Ability>)   {
}