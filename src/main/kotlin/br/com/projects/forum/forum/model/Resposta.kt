package br.com.projects.forum.forum.model

import java.time.LocalDateTime

data class Resposta(
    val id: Long? = null,
    val message: String,
    val criationDate: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic,
    val solution: Boolean
)