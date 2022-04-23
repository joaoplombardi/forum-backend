package br.com.projects.forum.forum.dto

import br.com.projects.forum.forum.model.TopicStatus
import java.time.LocalDateTime

data class TopicDTO(
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val creationDate: LocalDateTime
)