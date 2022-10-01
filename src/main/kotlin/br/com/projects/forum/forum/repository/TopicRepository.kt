package br.com.projects.forum.forum.repository

import br.com.projects.forum.forum.dto.TopicByCategoryDto
import br.com.projects.forum.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(courseName: String): List<Topic>
    fun findByCourseName(courseName: String, pagination: Pageable): Page<Topic>
    @Query("SELECT new br.com.projects.forum.forum.dto.TopicByCategoryDto(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicByCategoryDto>
}