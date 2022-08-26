package br.com.projects.forum.forum.repository

import br.com.projects.forum.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long> {
}