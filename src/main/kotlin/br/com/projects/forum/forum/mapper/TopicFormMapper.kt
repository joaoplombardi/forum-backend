package br.com.projects.forum.forum.mapper

import br.com.projects.forum.forum.dto.NewTopicForm
import br.com.projects.forum.forum.model.Topic
import br.com.projects.forum.forum.service.CourseService
import br.com.projects.forum.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService
    ): Mapper<NewTopicForm, Topic> {
    override fun map(topic: NewTopicForm): Topic {
        return Topic(
            title = topic.title,
            message = topic.message,
            course = courseService.findById(topic.courseId),
            author = userService.findById(topic.authorId)
        )
    }
}