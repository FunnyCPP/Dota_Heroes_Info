package com.v_kii_rom.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class AttributesApi(
    val AttributePrimary: String="",
    val AttributeBaseAgility: Int=0,
    val AttributeAgilityGain: Double= 0.0,
    val AttributeBaseStrength: Int=0,
    val AttributeStrengthGain: Double= 0.0,
    val AttributeBaseIntelligence: Int=0,
    val AttributeIntelligenceGain: Double= 0.0,
    val ArmorPhysical: Double= 0.0,
    val MagicalResistance: Double= 0.0,
    val StatusHealth:Double= 0.0,
    val StatusHealthRegen:Double= 0.0,
    val StatusMana:Double= 0.0,
    val StatusManaRegen: Double= 0.0,
    val MovementSpeed: Double= 0.0,
    val MovementTurnRate: Double= 0.0,
    val VisionDaytimeRange: Double= 0.0,
    val VisionNighttimeRange: Double= 0.0,
    val AttackCapabilities: String="",
    val AttackDamageMin: Int=0,
    val AttackDamageMax: Int=0,
    val AttackRate: Double= 0.0,
    val AttackAnimationPoint: Double= 0.0,
    val AttackAcquisitionRange: Double= 0.0,
    val AttackRange: Int=0,
    val ProjectileSpeed: Int=0,
    val Role: String="",
    val Rolelevels: String="",
    val Complexity: Int=0,
    val Team: String="",
    val HeroId: Int=0
) {
}