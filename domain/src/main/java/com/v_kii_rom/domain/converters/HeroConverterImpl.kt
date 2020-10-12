package com.v_kii_rom.domain.converters

import com.v_kii_rom.data.remote.models.HeroApi
import com.v_kii_rom.domain.models.Hero

class HeroConverterImpl {
    fun fromApiToUI(model: HeroApi): Hero{
        return Hero(id =  model.id, title = model.localized_name,
            attackType = if(model.attack_type == "Mellee"){
            0
            }
            else{ 1
            }, icon = "")
    }
}