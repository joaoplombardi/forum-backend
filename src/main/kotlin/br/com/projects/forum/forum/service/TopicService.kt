package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.dto.NewTopicForm
import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.dto.UpdateTopicForm
import br.com.projects.forum.forum.mapper.TopicFormMapper
import br.com.projects.forum.forum.mapper.TopicDtoMapper
import br.com.projects.forum.forum.model.Topic
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = ArrayList(),
    private val topicDtoMapper: TopicDtoMapper,
    private val topicFormMapper: TopicFormMapper
    ) {

    fun list(): List<TopicDTO> =
        topics.stream().map { topicDtoMapper.map(it) }.collect(Collectors.toList())

    fun findById(id: Long): TopicDTO {
        return topicDtoMapper.map(
            topics.stream().filter { it.id == id }.findFirst().get()
        )
    }

    fun register(form: NewTopicForm): TopicDTO {
        val topic = topicFormMapper.map(form)
        topic.id = topics.size.toLong() + 1
        topics = topics.plus(topic)
        return topicDtoMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicDTO{
        val topic = topics.stream().filter { it.id == form.id }.findFirst().get()
        val topicUpdated = Topic(
            id = form.id,
            title = form.title,
            message = form.message,
            author = topic.author,
            course = topic.course,
            responses = topic.responses,
            status = topic.status,
            creationDate = topic.creationDate
        )
        topics = topics.minus(topic).plus(topicUpdated)
        return topicDtoMapper.map(topicUpdated)
    }

    fun delete(id: Long) {
        val topic = topics.stream().filter { it.id == id }.findFirst().get()
        topics = topics.minus(topic)
    }

}