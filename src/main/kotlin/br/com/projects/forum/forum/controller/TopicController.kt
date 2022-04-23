package br.com.projects.forum.forum.controller

import br.com.projects.forum.forum.dto.NewTopicForm
import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.dto.UpdateTopicForm
import br.com.projects.forum.forum.service.TopicService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(): List<TopicDTO> =
        service.list()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicDTO =
        service.findById(id)

    @PostMapping
    fun register(@RequestBody @Valid dto: NewTopicForm) =
        service.register(dto)

    @PutMapping
    fun update(@RequestBody @Valid form: UpdateTopicForm) =
        service.update(form)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        service.delete(id)

}