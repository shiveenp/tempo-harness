package io.tempo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * An atomic representation of a single piece of data going to and from the LLM. The type of data or format doesn't matter,
 * everything should reasonably coalesce into this if possible to make things simple, type-safe and composable
 */
@Serializable
sealed class DataBlock {
    open fun genericTypeDescriptor(): String = this::class.simpleName ?: "Unknown"
}

@SerialName("text")
@Serializable
data class Text(val content: String) : DataBlock() {
    override fun genericTypeDescriptor(): String = "this is a simple text input, and should be interpreted as such"
}

@SerialName("tool_call")
@Serializable
data class ToolCall(val toolName: String, val toolId: String, val toolInput: JsonObject) : DataBlock() {
    override fun genericTypeDescriptor(): String = "this is a tool call request, and should be interpreted as such"
}

@SerialName("tool_result")
@Serializable
data class ToolResult(val toolName: String, val toolId: String, val result: DataBlock?, val isError: Boolean) : DataBlock() {
    override fun genericTypeDescriptor(): String = "this is a tool call result, and should be interpreted as such"
}
