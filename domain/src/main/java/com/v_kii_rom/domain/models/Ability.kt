package com.v_kii_rom.domain.models

data class Ability(
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