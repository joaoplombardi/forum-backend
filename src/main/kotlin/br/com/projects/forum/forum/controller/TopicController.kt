package br.com.projects.forum.forum.controller

import br.com.projects.forum.forum.model.*
import br.com.projects.forum.forum.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(): List<Topic> =
        service.list()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Topic =
        service.findById(id)

    @PostMapping
    fun register(@RequestBody topic: Topic) =
        service.register(topic)

}