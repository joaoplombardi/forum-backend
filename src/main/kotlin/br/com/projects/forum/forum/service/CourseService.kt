package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(var courses: List<Course>) {

    init {
        val course = Course(
            id = 1,
            name = "Curso maluco",
            category = "Maluquice"
        )
        courses = listOf(course)
    }

    fun findById(courseId: Long): Course =
        courses.stream().filter{ it.id == courseId}.findFirst().get()
}
