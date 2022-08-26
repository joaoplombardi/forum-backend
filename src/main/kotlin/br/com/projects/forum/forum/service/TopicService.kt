package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.dto.NewTopicForm
import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.dto.UpdateTopicForm
import br.com.projects.forum.forum.exception.NotFoundException
import br.com.projects.forum.forum.mapper.TopicFormMapper
import br.com.projects.forum.forum.mapper.TopicDtoMapper
import br.com.projects.forum.forum.model.Topic
import br.com.projects.forum.forum.repository.TopicRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicDtoMapper: TopicDtoMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found!"
    ) {

    fun list(): List<TopicDTO> =
        repository.findAll().stream().map { topicDtoMapper.map(it) }.collect(Collectors.toList())

    fun findById(id: Long): TopicDTO {
        return topicDtoMapper.map(
            repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        )
    }

    fun register(form: NewTopicForm): TopicDTO {
        val topic = topicFormMapper.map(form)
        repository.save(topic)
        return topicDtoMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicDTO{
        val topic = repository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        topic.title = form.title
        topic.message = form.message
        return topicDtoMapper.map(topic)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

}