package br.com.projects.forum.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(
    @field:NotEmpty
    @field:Size(min = 5, max = 50)
    val title: String,
    @field:NotEmpty
    @field:Size(min = 5, max = 256)
    val message: String,
    @field:NotNull
    val courseId: Long,
    @field:NotNull
    val authorId: Long
)