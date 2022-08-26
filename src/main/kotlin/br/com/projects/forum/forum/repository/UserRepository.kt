package br.com.projects.forum.forum.repository

import br.com.projects.forum.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}