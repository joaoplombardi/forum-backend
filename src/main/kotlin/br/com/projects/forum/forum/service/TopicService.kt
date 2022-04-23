package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.dto.NewTopicDTO
import br.com.projects.forum.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: List<Topic> = ArrayList(),
    private val courseService: CourseService,
    private val userService: UserService
    ) {

    fun list(): List<Topic> =
        topics

    fun findById(id: Long)=
        topics.stream().filter { it.id == id }.findFirst().get()

    fun register(dto: NewTopicDTO) {
        topics = topics.plus(
            Topic(
                id = topics.size.toLong() + 1,
                title = dto.title,
                message = dto.message,
                course = courseService.findById(dto.courseId),
                author = userService.findById(dto.authorId)
            )
        )
    }


}