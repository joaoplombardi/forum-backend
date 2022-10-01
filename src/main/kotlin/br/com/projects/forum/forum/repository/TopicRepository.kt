package br.com.projects.forum.forum.repository

import br.com.projects.forum.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(courseName: String): List<Topic>
    fun findByCourseName(courseName: String, pagination: Pageable): Page<Topic>
}