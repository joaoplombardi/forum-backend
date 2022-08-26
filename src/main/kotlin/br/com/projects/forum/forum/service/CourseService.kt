package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.model.Course
import br.com.projects.forum.forum.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun findById(courseId: Long): Course =
        repository.getById(courseId)

}
