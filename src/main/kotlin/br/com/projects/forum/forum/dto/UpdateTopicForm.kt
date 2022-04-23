package br.com.projects.forum.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateTopicForm(
    @field:NotNull val id: Long,
    @field:NotEmpty @field:Size(min = 5, max = 50) val title : String,
    @field:NotEmpty @field:Size(min = 5, max = 50) val message: String
)