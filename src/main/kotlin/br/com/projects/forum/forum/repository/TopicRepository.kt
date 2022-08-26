package br.com.projects.forum.forum.repository

import br.com.projects.forum.forum.model.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
}