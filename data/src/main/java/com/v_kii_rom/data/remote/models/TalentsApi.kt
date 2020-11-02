package com.v_kii_rom.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class TalentsApi(
    val tag: String="",
    val name: String="",
    val position: String=""
) {
}