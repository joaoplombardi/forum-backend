package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(private var topics: List<Topic> = ArrayList()) {

    fun list(): List<Topic> =
        topics

    fun findById(id: Long)=
        topics.stream().filter { it.id == id }.findFirst().get()

    fun register(topic: Topic) =
        topics.plus(topic)


}