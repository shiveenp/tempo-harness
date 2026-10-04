package io.tempo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A single message turn of the conversation can be from either side (user or LLM) of the harness
 */
@SerialName("message_turn")
@Serializable
data class MessageTurn(
    val currentRole: Role,
    val content: List<DataBlock>,
)
