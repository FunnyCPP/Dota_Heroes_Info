package com.v_kii_rom.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class AbilitiesApi(
    val tag: String="",
    val name: String="",
    val affects: String="",
    val description:String="",
    val notes:String="",
    val attrib: String="",
    val cooldown: String="",
    val manacost: String="",
    val lore: String="",
    val HasScepterUpgrade: Int=0
) {
}