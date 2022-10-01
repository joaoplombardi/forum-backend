package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.dto.NewTopicForm
import br.com.projects.forum.forum.dto.TopicByCategoryDto
import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.dto.UpdateTopicForm
import br.com.projects.forum.forum.exception.NotFoundException
import br.com.projects.forum.forum.mapper.TopicFormMapper
import br.com.projects.forum.forum.mapper.TopicDtoMapper
import br.com.projects.forum.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicDtoMapper: TopicDtoMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found!"
    ) {

    fun list(
        courseName: String?,
        pagination: Pageable
    ): Page<TopicDTO> {
        val topics = if (courseName.isNullOrEmpty()) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(courseName, pagination)
        }

        println(pagination)

        return topics.map { topicDtoMapper.map(it) }
    }


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

    fun report(): List<TopicByCategoryDto> {
        return repository.report()
    }

}