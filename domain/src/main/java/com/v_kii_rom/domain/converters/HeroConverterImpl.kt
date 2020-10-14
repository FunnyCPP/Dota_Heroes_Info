package com.v_kii_rom.domain.converters

import com.v_kii_rom.data.remote.models.HeroApi
import com.v_kii_rom.domain.models.Hero

class HeroConverterImpl {
    fun fromApiToUI(model: HeroApi): Hero{
        return Hero(id =  model.id,name = model.name, localized_name = model.localized_name,
         primary_attr = model.primary_attr, attack_type = model.attack_type, roles = model.roles,
         img = model.img, icon = model.icon,
         base_health = model.base_health,
         base_mana = model.base_mana, base_mana_regen= model.base_mana_regen,
         base_armor= model.base_armor, base_mr= model.base_mr,
         base_attack_min = model.base_attack_min, base_attack_max = model.base_attack_max,
         base_str = model.base_str, base_agi = model.base_agi, base_int =model.base_int,
         str_gain = model.str_gain, agi_gain = model.agi_gain, int_gain= model.int_gain,
         attack_range =model.attack_range, projectile_speed = model.move_speed,
         attack_rate =model.attack_rate, move_speed= model.move_speed,
         turn_rate=model.turn_rate, cm_enabled=model.cm_enabled, legs = model.legs, pro_ban = model.pro_ban,
            hero_id= model.hero_id, pro_win = model.pro_win ,pro_pick=model.pro_pick)
    }
}