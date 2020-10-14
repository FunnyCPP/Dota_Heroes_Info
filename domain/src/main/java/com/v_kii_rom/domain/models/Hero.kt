package com.v_kii_rom.domain.models

data class Hero(val id: Int, val name: String, val localized_name: String,
                val primary_attr: String, val  attack_type: String, val roles :List<String>,
                val img: String, val icon: String,
                val base_health: Int,
                val base_mana: Int, val base_mana_regen: Double,
                val base_armor: Double, val  base_mr: Int,
                val base_attack_min: Int, val base_attack_max: Int,
                val base_str: Int, val base_agi:Int, val base_int: Int,
                val str_gain: Double, val agi_gain: Double, val int_gain: Double,
                val attack_range: Int, val projectile_speed:Int,
                val attack_rate:Double, val move_speed: Int,
                val turn_rate: Double, val cm_enabled: String, val legs: Int, val pro_ban: Int, val hero_id:  Int, val  pro_win: Int, val pro_pick: Int)   {
}