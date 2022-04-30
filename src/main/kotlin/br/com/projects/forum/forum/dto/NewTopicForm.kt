package br.com.projects.forum.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(
    @field:NotEmpty(message = "Title cannot be null")
    @field:Size(min = 5, max = 50, message = "Title cannot be null")
    val title: String,
    @field:NotEmpty(message = "Message cannot be null")
    @field:Size(min = 5, max = 256, message = "Message cannot be null")
    val message: String,
    @field:NotNull(message = "CourseId cannot be null")
    val courseId: Long,
    @field:NotNull(message = "AuthorId cannot be null")
    val authorId: Long
)