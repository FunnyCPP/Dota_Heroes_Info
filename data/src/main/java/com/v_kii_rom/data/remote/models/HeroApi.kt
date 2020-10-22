package com.v_kii_rom.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class HeroApi(val tag: String="",
                   val name: String="",
                   val bio: String="",
                   val hype: String="",
                   val abilities: List<AbilitiesApi>,
                  // val talents: List<TalentsApi>,
                   val attributes: AttributesApi,
                   val abilities_aghs:List<AbilitiesApi>,
                   val abilities_special: List<AbilitiesApi>,
                   val abilities_hidden: List<AbilitiesApi>) {


}
