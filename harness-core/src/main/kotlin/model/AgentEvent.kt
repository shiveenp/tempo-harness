package io.tempo.model

/**
 * Core interface through which an LLM will communicate with the harness. The goal is to encapsulate all valid suppported
 * events in a type interface.
 */
sealed interface AgentEvent {
    data class TurnStarted(val turn: Int) : AgentEvent
    data class TextDelta(val text: String) : AgentEvent
    data class ToolCallRequested(val call: ToolCall) : AgentEvent
    data class ApprovalRequired(val call: ToolCall) : AgentEvent
    data class ToolCallFinished(val call: ToolCall, val result: ToolResult, val durationMs: Long) : AgentEvent
    data class UsageReported(val usage: Usage) : AgentEvent
    data class Done(val stopReason: StopReason, val totalUsage: Usage) : AgentEvent
    data class Failed(val error: AgentError) : AgentEvent
}

data class Usage(
    val inputTokens: Int,
    val outputTokens: Int,
)

enum class StopReason {
    END_TURN,
    MAX_TURNS,
    MAX_TOKENS,
    ERROR,
}

sealed interface AgentError {
    data class ProviderError(val message: String) : AgentError
    data class Timeout(val afterMs: Long) : AgentError
    data class ToolExecutionFailed(val toolName: String, val message: String) : AgentError
}
