package br.com.projects.forum.forum.model

import java.time.LocalDateTime

data class Topic (
    val id: Long? = null,
    val title: String,
    val message: String,
    val criationDate: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: TopicStatus = TopicStatus.NOT_RESOLVED,
    val responses: List<Resposta> = ArrayList()
)