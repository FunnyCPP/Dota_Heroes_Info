package com.v_kii_rom.domain.converters

import com.v_kii_rom.data.remote.models.AbilitiesApi
import com.v_kii_rom.data.remote.models.AttributesApi
import com.v_kii_rom.data.remote.models.HeroApi
import com.v_kii_rom.domain.models.Ability
import com.v_kii_rom.domain.models.Attribut
import com.v_kii_rom.domain.models.Hero



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
    private fun fromAbilitiestoAbility(model: AbilitiesApi): Ability {
        var s: String = model.description.replace("<br/>","")
        s=s.replace("<font color=\"#bdc3c7\">"," ")
        s=s.replace("</font>","")
        s=s.replace("<font color=\"#2ecc71\">"," ")
        s=s.replace("<br />","")
        s=s.replace("<font color=\"#e74c3c\">"," ")
        s=s.replace("\\n"," ")
        s=s.replace("%%","%")
        s=s.replace("<font color=\"#3498db\">"," ")
        s=s.replace("<font color=\\\"#ff0000\\\">", " ")
        s=s.replace("<font color=\\\"#9acd32\\\">", " ")
        s=s.replace("<font color=\\\"#87ceeb\\\">"," ")
        return Ability(
            tag= model.tag,
         name = model.name,
         affects = model.affects,
         description= s,
         notes=  model.notes,
         attrib= model.attrib,
         cooldown= model.cooldown,
         manacost= model.manacost,
         lore=model.lore,
         HasScepterUpgrade= model.HasScepterUpgrade
        )
    }
    private fun fromAttributesToAttribut(model: AttributesApi): Attribut{
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