package com.v_kii_rom.domain.converters

import com.v_kii_rom.data.remote.models.AbilitiesApi
import com.v_kii_rom.data.remote.models.AttributesApi
import com.v_kii_rom.data.remote.models.HeroApi
import com.v_kii_rom.data.remote.models.TalentsApi
import com.v_kii_rom.domain.models.Ability
import com.v_kii_rom.domain.models.Attribut
import com.v_kii_rom.domain.models.Hero
import com.v_kii_rom.domain.models.Talent


class HeroConverterImpl {
    fun fromApiToUI(model: HeroApi): Hero{
        return Hero(
            tag = model.tag,
            name=model.name,
            bio= model.bio,
            hype= model.hype,
            abilities= model.abilities.map{ ability -> fromAbilitiestoAbility(model = ability)},
            //talents = model.talents.map { talent -> fromTalentsToTalent(model = talent) } ,
            attributes = fromAttributesToAttribut(model = model.attributes),
            abilities_aghs= model.abilities_aghs.map{ ability -> fromAbilitiestoAbility(model = ability)},
            abilities_special=model.abilities_special.map{ ability -> fromAbilitiestoAbility(model = ability)},
            abilities_hidden = model.abilities_hidden.map{ ability -> fromAbilitiestoAbility(model = ability)})
    }
    fun fromAbilitiestoAbility(model: AbilitiesApi): Ability {
        return Ability(
            tag= model?.tag,
         name = model?.name,
         affects = model?.affects,
         description= model?.description,
         notes=  model.notes,
         attrib= model?.attrib,
         cooldawn= model?.cooldawn,
         manacost= model?.manacost,
         lore=model?.lore,
         HasScepterUpgrade= model?.HasScepterUpgrade
        )
    }
    fun fromAttributesToAttribut(model: AttributesApi): Attribut{
        return Attribut(
            AttributePrimary = model.AttributePrimary,
            AttributeBaseAgility = model.AttributeBaseAgility,
            AttributeAgilityGain = model.AttributeAgilityGain,
            AttributeBaseStrength = model.AttributeBaseStrength,
            AttributeStrengthGain = model.AttributeStrengthGain,
            AttributeBaseIntelligence = model.AttributeBaseIntelligence,
            AttributeIntelligenceGain = model.AttributeIntelligenceGain,
            ArmorPhysical = model.ArmorPhysical,
            MagicalResistance = model.MagicalResistance,
            StatusHealth = model.StatusHealth,
            StatusHealthRegen = model.StatusHealthRegen,
            StatusMana = model.StatusMana,
            StatusManaRegen = model.StatusManaRegen,
            MovementSpeed = model.MovementSpeed,
            MovementTurnRate = model.MovementTurnRate,
            VisionDaytimeRange = model.VisionDaytimeRange,
            VisionNighttimeRange = model.VisionNighttimeRange,
            AttackCapabilities = model.AttackCapabilities,
            AttackDamageMin = model.AttackDamageMin,
            AttackDamageMax = model.AttackDamageMax,
            AttackRate = model.AttackRate,
            AttackAnimationPoint = model.AttackAnimationPoint,
            AttackAcquisitionRange = model.AttackAcquisitionRange,
            AttackRange = model.AttackRange,
            ProjectileSpeed = model.ProjectileSpeed,
            Role = model.Role,
            Rolelevels = model.Rolelevels,
            Complexity = model.Complexity,
            Team = model.Team,
            HeroId = model.HeroId
        )
    }
   // fun fromTalentsToTalent(model: TalentsApi): Talent {
   //     return Talent(tag = model.tag, name = model.name, position = model.position)

  //  }
}